---
description: >
  Skill de documentação incremental para sistemas Java/Spring + Fronts com vault Obsidian.
  Cobre P1–P6: setup, bootstrap, execução incremental, sync Git e retomada após falha.
  Carregue este arquivo antes de qualquer ação de documentação.
applyTo: "obsidian/**"
---

# DOC-WORKFLOW SKILL

## Mapa de processos

| Processo | Nome | Quando executar |
|---|---|---|
| P1 | Setup Raiz | Uma vez por dev — adiciona entrada em doc-config.json versionado (DocSetup) |
| P2 | Setup Repositório | Uma vez por repositório — back e cada front (DocSetup) |
| P3 | Bootstrap | Uma vez por repositório — logo após P2 (DocAgent) |
| P4 | Execução Incremental | A cada mudança de código (DocAgent) |
| P6 | Retomada | Quando execução for interrompida (DocAgent) |

---

## Regras absolutas (NUNCA violar)

- SEMPRE executar `node obsidian/scripts/scan-project.js` ANTES de ler `diff.json` — nunca confiar no diff.json já existente no disco
- Para geração de `.md`: perguntar UMA VEZ quantos arquivos processar e executar continuamente sem pedir confirmação entre batches/grupos. Reportar progresso inline.
- NUNCA processar mais de 5 arquivos por grupo (mas executar grupos consecutivos sem parar)
- NUNCA sobrescrever conteúdo dentro de `## MANUAL`
- NUNCA usar caminhos hardcoded — sempre ler `rootPath` de `obsidian/doc-config.json`
- NUNCA fazer push sem pull antes
- NUNCA processar `Sem título.md` ou `Untitled.md` → quarentenar em `vault/invalid/`
- NUNCA gerar documentação genérica — sempre refletir o código real
- NUNCA criar arquivos pelo UI do Obsidian — sempre pelo filesystem
- NUNCA executar fora do ambiente DEV
- NÃO documentar linha por linha — o foco é o **comportamento**, não a sintaxe
- SEMPRE explicar: **o que faz, por que existe, como se conecta ao sistema**
- SEMPRE usar links `[[filename|NomeVisual]]` com alias
- SEMPRE preencher `contextObsidian.md` ANTES de criar qualquer `.md` no vault
- ANTES de documentar qualquer classe, **ler o código-fonte de TODAS as classes que ela referencia** (dependências injetadas, classes usadas em parâmetros/retornos, exceções lançadas, DTOs, assemblers). Só gerar o `.md` depois de entender o contexto completo — regras de negócio, validações, fluxos e como a classe se encaixa no sistema.

---

## Visão geral da arquitetura

O sistema é composto por **dois ambientes com responsabilidades separadas**:

**Dentro de cada repositório — `obsidian/`**
Cada repositório (back ou front) possui sua própria pasta `obsidian/` com scanner, cache e diff **exclusivos daquele repositório**. O scanner não sabe da existência dos outros repositórios.

**Fora dos repositórios — pasta raiz do sistema**
Uma pasta independente contém o vault do Obsidian. É o ponto de convergência de toda a documentação. Git é gerenciado manualmente pelo desenvolvedor (ou pelo Copilot quando solicitado).

```
┌──────────────────────────────────────┐
│  REPOSITÓRIO BACK                    │
│  └── obsidian/                       │
│       scan · cache · diff            │
│       └── gera .md em ──────────┐    │
└─────────────────────────────────│────┘
                                  │
┌─────────────────────────────────│────┐
│  REPOSITÓRIO FRONT-<NOME>       │    │
│  └── obsidian/                 │    │
│       scan · cache · diff       │    │
│       └── gera .md em ──────────┤    │
└─────────────────────────────────│────┘
                                  ▼
                    ┌─────────────────────────┐
                    │  <rootPath>/            │
                    │  ├── vault/             │
                    │  │   ├── back/          │
                    │  │   ├── front-<nome>/  │
                    │  │   └── shared/        │
                    │  └── .git/              │
                    └─────────────────────────┘
```

**Sobre `/shared/`:** a pasta existe no vault mas é **preenchida manualmente pelo desenvolvedor**. O sistema não detecta componentes compartilhados automaticamente — cada repositório é independente e o scanner não cruza informações entre repos. O Copilot pode sugerir o uso de `/shared/` ao perceber duplicação, mas a decisão é sempre do desenvolvedor.

---

## P1 no doc-setup.agent.md

O P1 é executado **uma vez por desenvolvedor** — cada dev que clona o projeto precisa rodar.
`obsidian/doc-config.json` é **versionado e compartilhado** — contém entradas de todos os devs,
diferenciadas pelo `git config user.name`.
O fluxo completo está no `doc-setup.agent.md` (3 cenários: clonar repo existente, reusar pasta local, criar do zero).

---

## Leitura inicial obrigatória (antes de qualquer ação)

1. Ler `obsidian/doc-config.json` → identificar dev via `git config user.name` → obter `rootPath`
2. Executar `node obsidian/scripts/scan-project.js` → atualizar diff
3. Ler `obsidian/scripts/diff.json` → verificar estado
4. Ler `obsidian/context/contextObsidian.md` → verificar execução em andamento
   - Se status = `IN_PROGRESS` → oferecer P6 antes de qualquer outra ação

---

## Estrutura do vault

```
<rootPath>/
├── vault/
│   ├── overview/
│   ├── shared/               # preenchido manualmente pelo dev
│   ├── back/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── repositories/
│   │   ├── entities/
│   │   ├── flows/
│   │   └── infra/
│   ├── front-<nome>/         # criado no P2 de cada front
│   │   ├── pages/
│   │   ├── components/
│   │   ├── services/
│   │   └── infra/
│   ├── logs/
│   ├── invalid/              # quarentena de arquivos inválidos
│   └── archive/
│       ├── back/
│       ├── shared/
│       └── front-<nome>/
└── .git/                     # repositório Git (init no P1)
```

---

## Convenção de nomes — kebab-case com prefixo de tipo

### Back-end

| Tipo | Classe | Filename |
|---|---|---|
| Service | `UserService.java` | `service-user.md` |
| Controller | `AuthController.java` | `controller-auth.md` |
| Repository | `ProductRepository.java` | `repository-product.md` |
| Entity | `OrderEntity.java` | `entity-order.md` |
| Infra/Config | `DataSourceConfig.java` | `infra-datasource-config.md` |

### Front-end

| Tipo | Arquivo | Filename |
|---|---|---|
| Page | `LoginPage.tsx` | `page-login.md` |
| Component | `AuthButton.tsx` | `component-auth-button.md` |
| Service | `authService.ts` | `service-auth.md` |

---

## Estrutura de cada `.md` no vault

> **Regra de leitura profunda:** antes de gerar qualquer `.md`, o agente DEVE ler o código-fonte
> da classe-alvo E de todas as classes que ela referencia (dependências, exceções, DTOs,
> assemblers, entities usadas). O objetivo é entender o contexto completo antes de escrever.

```markdown
---
id: svc-001          # tipo-número sequencial: svc, ctrl, repo, ent, page, comp
scope: back          # back | front-<nome>
status: active       # active | deleted
---

# NomeClasse

## AUTO-GERADO

### 📋 Propósito
<!-- O que esta classe faz e por que ela existe no sistema.
     Explicar em 2-4 frases o papel dela na arquitetura. -->

### ⚙️ Comportamento e Regras de Negócio
<!-- Explicação detalhada da lógica interna:
     - Regras de negócio implementadas
     - Validações realizadas
     - Fluxos condicionais (if/else relevantes)
     - Exceções lançadas e em que cenário
     - Transações e seus escopos
     - Efeitos colaterais (eventos, logs, etc.)
     Não listar linha por linha — agrupar por responsabilidade. -->

### 🔌 Conexões no Sistema
<!-- Como esta classe se encaixa na arquitetura:
     - Quem a chama e por quê
     - O que ela chama e por quê
     - Fluxo de dados: de onde vem o input e para onde vai o output
     - Impacto: o que quebra se ela parar de funcionar -->

### 📡 Endpoints / Métodos Públicos
<!-- Tabela com a API pública da classe.
     Para Controllers: endpoints HTTP.
     Para Services: métodos públicos com parâmetros e retorno.
     Para Repositories: queries customizadas.
     Para Entities: campos e relacionamentos JPA. -->

### 🔗 Dependências Ativas
- [[repository-user|UserRepository]]
- [[service-auth|AuthService]]

### 🗄️ Dependências Arquivadas
- [[service-payment-old|OldPaymentService]]

---

### 📡 Utilizado por (Ativos)
- [[controller-user|UserController]]

### 🧟 Utilizado por (Arquivados)
- [[controller-old|OldController]]

---

## MANUAL

<!-- Conteúdo inserido pelo desenvolvedor -->
<!-- Esta seção NUNCA será sobrescrita pelo sistema -->
```

---

## Estrutura do `contextObsidian.md`

```markdown
# contextObsidian

> Gerado em: YYYY-MM-DD HH:MM:SS
> Repositório: <nome>
> Escopo vault: <back|front-<nome>>
> Grupo: <N> de <X>
> Status: IN_PROGRESS

---

## 📦 Grupo atual

| # | Arquivo | Ação | Destino no vault | Status |
|---|---|---|---|---|
| 1 | UserService.java | CREATE | back/services/service-user.md | ⏳ PENDING |
| 2 | AuthController.java | UPDATE | back/controllers/controller-auth.md | ⏳ PENDING |

---

## 📄 Conteúdo planejado

### [1] service-user.md — CREATE
(conteúdo completo do .md planejado)
```

---

## Ações por arquivo

| Ação | Comportamento |
|---|---|
| **CREATE** | Criar `.md` com conteúdo completo; registrar id e hash no cache |
| **UPDATE** | Atualizar APENAS `## AUTO-GERADO`; preservar `## MANUAL` intacto |
| **RENAME** | Renomear arquivo; preservar todo conteúdo; manter `id`; atualizar links |
| **DELETE** | Mover para `archive/`; adicionar cabeçalho de status; nunca excluir |

### Cabeçalho de soft delete

```markdown
---
id: <id>
status: deleted
scope: <escopo>
deleted_at: YYYY-MM-DD
replaced_by: "[[filename|NomeVisual]]"
---

> ⚠️ **COMPONENTE REMOVIDO DO PROJETO**
> Mantido apenas para referência histórica.
```

---

## Prioridade de processamento

| Prioridade | Back | Front |
|---|---|---|
| 🔴 Alta | Controllers, Services com regra de negócio | Pages, Services |
| 🟡 Média | Entities, Repositories | Components |
| 🟢 Baixa | Infra, Config, Utils | Infra, Config, Utils |

---

## Schemas JSON

### `obsidian/doc-config.json` (versionado e compartilhado)

```json
{
  "developers": {
    "<git-user-name>": {
      "rootPath": "<caminho-absoluto>"
    }
  }
}
```
Cada dev é identificado por `git config user.name`.

### `diff.json`

```json
{
  "created": ["caminho/relativo/arquivo"],
  "modified": ["..."],
  "deleted": ["..."],
  "renamed": [{ "from": "...", "to": "..." }]
}
```

---

## Sincronização Git (manual)

> A sincronização Git é **sempre manual** — disparada pelo desenvolvedor ou pelo Copilot
> quando solicitado pelo desenvolvedor ao final de um batch de documentação.

### Quando sincronizar

Ao final de P3 (Bootstrap) ou P4 (Execução Incremental), o Copilot pergunta:
> "Documentação gerada. **Quer subir commit do cofre do Obsidian?** (sim/não)"

Se **sim**, o Copilot executa diretamente no `<rootPath>`:
```
1. cd <rootPath>
2. git add vault/
3. git commit -m "docs: sync [<developer>] <timestamp>"
4. git pull --rebase origin main
5. git push origin main
```

Se **não**, os arquivos ficam apenas locais. O dev pode commitar quando quiser.

### Tratamento de conflito

Conflitos são raros (cada dev trabalha em escopos distintos: `vault/back/`, `vault/front-<nome>/`).
Se ocorrer: o dev resolve manualmente.

---

## Checklist por execução P4

- [ ] Scanner executado ANTES de ler `diff.json`
- [ ] `obsidian/doc-config.json` lido → dev identificado via `git config user.name` → `rootPath` disponível
- [ ] `contextObsidian.md` resetado e preenchido ANTES de gerar `.md`
- [ ] Grupo de exatamente 5 arquivos (ou menos no último grupo)
- [ ] Filenames em kebab-case com prefixo de tipo correto
- [ ] Links com formato `[[filename|NomeVisual]]`
- [ ] `## MANUAL` preservado integralmente em UPDATEs
- [ ] Soft delete: arquivo movido para `archive/`, nunca deletado
- [ ] `doc-cache.json` atualizado após cada arquivo
- [ ] `contextObsidian.md` com ✅ DONE em cada item concluído
- [ ] Verificação final de links e índices após último grupo
- [ ] Conteúdo documenta comportamento, não sintaxe

---

## Checklist global do sistema

**Pasta raiz (P1 — feito uma vez):**

- [ ] Pasta raiz criada (ou reusada, se já existia)
- [ ] Entrada do dev adicionada em `obsidian/doc-config.json` (versionado)
- [ ] Estrutura de `vault/` com todas as subpastas
- [ ] Repositório Git inicializado com remote e `.gitignore`

**Por repositório (P2 + P3 — feito uma vez por repo):**

- [ ] `obsidian/` com estrutura completa
- [ ] `scan-project.js` implementado
- [ ] `copilot-instructions.md` na raiz do repositório
- [ ] `.gitignore` atualizado
- [ ] Bootstrap (P3) executado com sucesso

**Qualidade contínua (P4 — toda execução):**

- [ ] `contextObsidian` preenchido antes de gerar `.md`
- [ ] Filenames em kebab-case com prefixo de tipo
- [ ] Links sempre `[[filename|NomeVisual]]` com alias
- [ ] Links com estado semântico (ativo/arquivado)
- [ ] Arquivos inválidos quarentenados em `vault/invalid/`
- [ ] Soft delete em `vault/archive/<escopo>/` (nunca deletar)
- [ ] Conteúdo MANUAL sempre preservado
- [ ] Índices sincronizados
- [ ] Execução restrita ao ambiente DEV
