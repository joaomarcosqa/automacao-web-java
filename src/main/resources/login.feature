#language: pt

@integrado
Funcionalidade: Abrir Google

  @fecharNavegador
  Cenario: Teste de execucao
    Dado Que acesso a pagina da"Westwing"
    E Preecher o campo usuario "Email" com "hehasa9603@sumwan.com"
    E Preecher o campo senha "Senha" com "1234567"
    Entao Efetuo login com sucesso "Validar" com "Meus pedidos"
