package br.unicamp.padroescriacionais.legacy;

import br.unicamp.padroescriacionais.legacy.domain.ConfiguracaoSistema;
import br.unicamp.padroescriacionais.legacy.service.ConfiguracaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguracaoSistemaTest {

    private ConfiguracaoSistema config;

    @BeforeEach
    void setUp() {
        // Recupera a instância única do Singleton
        config = ConfiguracaoSistema.getInstance();
        
        // Como o Singleton mantém estado global (vazamento de estado), 
        // precisamos resetar os valores antes de cada teste para garantir isolamento.
        config.setNomeEmpresa("Empresa Teste");
        config.setAmbiente("DEV");
        config.setDiretorioExportacao("/tmp");
        config.setDebugAtivo(false);
    }

    @Test
    void deveGarantirQueInstanciaEUnicaSingleton() {
        // Testa a principal regra do Singleton: só existe uma instância na memória
        ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstance();
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstance();

        assertSame(config1, config2, "Ambas as variáveis devem apontar para a mesma instância na memória");
    }

    @Test
    void alteracaoEmUmaReferenciaAfetaTodasAsOutras() {
        ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstance();
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstance();

        // Altera o ambiente através da config1
        config1.setAmbiente("PROD");

        // A config2 deve enxergar a alteração, provando o estado global
        assertEquals("PROD", config2.getAmbiente());
    }

    @Test
    void devePermitirAlteracaoDeAmbiente() {
        config.setAmbiente("PROD");
        assertEquals("PROD", config.getAmbiente());
    }

    @Test
    void devePermitirAlteracaoDeDebug() {
        config.setDebugAtivo(true);
        assertTrue(config.isDebugAtivo());
    }

    @Test
    void devePermitirAlteracaoDeDiretorio() {
        config.setDiretorioExportacao("/novo/diretorio");
        assertEquals("/novo/diretorio", config.getDiretorioExportacao());
    }

    @Test
    void configuracaoServiceDeveRetornarAInstanciaSingleton() {
        ConfiguracaoService service = new ConfiguracaoService();
        assertNotNull(service.getConfiguracao());
        assertSame(ConfiguracaoSistema.getInstance(), service.getConfiguracao(), 
                   "O serviço deve usar a mesma instância global do Singleton");
    }
}