# Meu-jogo-em-Java

Em um projeto comandado pelo o professor Ramon Mayor Martins pelo o Instituto Federal de Santa Catarina. 
Criei o jogo Ping Pong, um jogo simples que não requer muitos comandos, apenas criado para diversão.
Espero que gostem.


INSTITUTO FEDERAL DE SANTA CATARINA


_________________________________________________________________________


 Jogo de Ping Pong em Java
Objetivo do Projeto
Desenvolvi um jogo simples de Ping Pong (Pong) usando Java com a biblioteca Swing. O objetivo do jogo é que dois jogadores controlem raquetes e disputem para ver quem consegue marcar 5 pontos primeiro.

 Tecnologias Utilizadas
Java


Biblioteca Swing (interface gráfica)


Thread e Eventos de Teclado para controle de jogo em tempo real



 Lógica do Jogo
Duas raquetes controladas por teclas:


Jogador 1: W e S


Jogador 2: ↑ e ↓


Uma bola se movimenta e quica nas bordas e nas raquetes.


Se a bola passar por uma das laterais:


O adversário marca ponto


A bola é reiniciada no centro


O jogo termina quando um dos jogadores atinge 5 pontos.


Exibe uma mensagem de vitória e reinicia o placar após 3 segundos.



 Componentes principais
JFrame e JPanel: criam a janela do jogo.


Rectangle: usado para desenhar as raquetes e a bola.


paintComponent(Graphics g): desenha o fundo, a linha do meio, raquetes, bola e placar.


run(): executa o loop do jogo (movimentação e colisões).


keyPressed() / keyReleased(): controlam o movimento das raquetes com as teclas.


resetBall() e resetGame(): reiniciam a bola ou o jogo.



