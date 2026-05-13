package br.unicamp.padroescriacionais.legacy.domain;

public class ConfiguracaoSistema {

    // Passo 2: Atributo estático e privado que guarda a única instância
    private static ConfiguracaoSistema instance;

    private String nomeEmpresa;
    private String ambiente;
    private String diretorioExportacao;
    private boolean debugAtivo;

    // Passo 1: Construtor privado (ninguém de fora pode fazer 'new')
    private ConfiguracaoSistema() {
        // Inicialização com valores padrão seguros e eles só poderão ser alterados posteriormente usando os Setters.
        this.nomeEmpresa = "Empresa Padrão";
        this.ambiente = "Desenvolvimento";
        this.diretorioExportacao = "/export";
        this.debugAtivo = false;
    }

    // Passo 3: Método de acesso global
    // O 'synchronized' garante que duas threads não criem instâncias diferentes ao mesmo tempo
    public static synchronized ConfiguracaoSistema getInstance() {
        if (instance == null) {
            instance = new ConfiguracaoSistema();
        }
        return instance;
    }

    // Getters e Setters permanecem iguais
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getDiretorioExportacao() {
        return diretorioExportacao;
    }

    public void setDiretorioExportacao(String diretorioExportacao) {
        this.diretorioExportacao = diretorioExportacao;
    }

    public boolean isDebugAtivo() {
        return debugAtivo;
    }

    public void setDebugAtivo(boolean debugAtivo) {
        this.debugAtivo = debugAtivo;
    }
}