/// <reference types="vite/client" />

interface ImportMetaEnv {
    readonly VITE_RYOKO_API_BASEURL: string;
}

interface ImportMeta {
    readonly env: ImportMetaEnv;
}
