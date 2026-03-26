# Copilot Instructions

## Documentação incremental

Antes de qualquer ação de documentação, leia e siga:
`obsidian/prompts/documentation-prompt.md`

### Regras obrigatórias

- Escopo deste repositório: `back`
- Gerar todos os `.md` em: `vault/back/` (usar rootPath de obsidian/doc-config.json)
- Executar o fluxo baseado em `obsidian/scripts/diff.json`
- Trabalhar em batches de 5 — nunca processar tudo de uma vez
- Nunca sobrescrever `## MANUAL`
- Nunca criar arquivos pelo UI do Obsidian — sempre pelo filesystem
- Nunca avançar para a próxima etapa sem confirmação do desenvolvedor
- Não documentar linha por linha — foco no comportamento
- Explicar: o que faz, por que existe, como se conecta ao sistema
