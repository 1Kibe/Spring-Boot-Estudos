'use strict';
const fs     = require('fs');
const path   = require('path');
const crypto = require('crypto');

// ── Configuração ──────────────────────────────────────────
const repoRoot  = process.cwd();
const cachePath = path.join(repoRoot, 'obsidian', 'scripts', 'doc-cache.json');
const diffPath  = path.join(repoRoot, 'obsidian', 'scripts', 'diff.json');

// Diretórios ignorados (nunca entrar)
const IGNORE_DIRS = new Set([
  'node_modules', 'target', 'build', 'dist',
  '.git', '.idea', '.vscode', '.settings', '.gradle',
  '.next', '.nuxt', '.cache', '__pycache__',
  '.github', 'obsidian'
]);

// Extensões de arquivos-fonte relevantes para documentação
const INCLUDE_EXT = new Set([
  // Back-end (Java/Spring)
  '.java', '.properties', '.yml', '.yaml', '.xml', '.sql',
  // Front-end
  '.ts', '.tsx', '.js', '.jsx', '.vue', '.css', '.scss',
  // Config
  '.json'
]);

// ── Funções auxiliares ────────────────────────────────────
function shouldIgnore(relPath) {
  return relPath.split(path.sep).some(p => IGNORE_DIRS.has(p));
}

function isRelevant(filename) {
  return INCLUDE_EXT.has(path.extname(filename).toLowerCase());
}

function hashFile(filePath) {
  return crypto.createHash('sha256').update(fs.readFileSync(filePath)).digest('hex');
}

function walkDir(dir, files = []) {
  if (!fs.existsSync(dir)) return files;
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const fullPath = path.join(dir, entry.name);
    const rel      = path.relative(repoRoot, fullPath);
    if (shouldIgnore(rel)) continue;
    if (entry.isDirectory()) {
      walkDir(fullPath, files);
    } else if (isRelevant(entry.name)) {
      files.push({ fullPath, rel, name: entry.name });
    }
  }
  return files;
}

// ── Ler cache existente ───────────────────────────────────
const cache    = fs.existsSync(cachePath) ? JSON.parse(fs.readFileSync(cachePath, 'utf8')) : {};
const diff     = { created: [], modified: [], deleted: [], renamed: [] };
const newCache = {};

// ── Escanear arquivos-fonte do projeto ────────────────────
const files = walkDir(repoRoot);

for (const file of files) {
  const stat         = fs.statSync(file.fullPath);
  const lastModified = stat.mtimeMs.toString();
  const cached       = cache[file.rel];

  if (!cached) {
    const hash         = hashFile(file.fullPath);
    const originalPath = Object.keys(cache).find(
      k => cache[k].hash === hash && !newCache[k]
    );
    if (originalPath) {
      diff.renamed.push({ from: originalPath, to: file.rel });
    } else {
      diff.created.push(file.rel);
    }
    newCache[file.rel] = { hash, lastModified };
  } else if (cached.lastModified !== lastModified) {
    const hash = hashFile(file.fullPath);
    if (hash !== cached.hash) diff.modified.push(file.rel);
    newCache[file.rel] = { hash, lastModified };
  } else {
    newCache[file.rel] = cached;
  }
}

// ── Detectar deletados ────────────────────────────────────
for (const key of Object.keys(cache)) {
  if (!newCache[key] && !diff.renamed.find(r => r.from === key)) {
    diff.deleted.push(key);
  }
}

// ── Salvar resultados ─────────────────────────────────────
fs.writeFileSync(cachePath, JSON.stringify(newCache, null, 2));
fs.writeFileSync(diffPath,  JSON.stringify(diff, null, 2));

const total = diff.created.length + diff.modified.length +
              diff.deleted.length + diff.renamed.length;
console.log('[scan] ──────────────────────────────────');
console.log(`[scan] Criados:    ${diff.created.length}`);
console.log(`[scan] Modificados: ${diff.modified.length}`);
console.log(`[scan] Deletados:  ${diff.deleted.length}`);
console.log(`[scan] Renomeados: ${diff.renamed.length}`);
console.log(`[scan] Total:      ${total}`);
console.log('[scan] ──────────────────────────────────');
