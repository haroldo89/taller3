export class Constantes {

    ANCHO_MAXIMO_FRAME: number = 950;//Ancho máximo del frame en pixeles
    ALTO_MAXIMO_FRAME: number = 450;//Ancho máximo del frame en pixeles

    ANCHO_MAXIMO_CANVAS: number = 350;//Ancho máximo del canvas en pixeles
    ALTO_MAXIMO_CANVAS: number = 350;//Alto máximo del canvas en pixeles

    ALTO_DEDO: number = 2 * this.ANCHO_MAXIMO_CANVAS / 10;//Alto del dedo en pixeles
    ALTO_MANO: number = 2 * this.ANCHO_MAXIMO_CANVAS / 10;//Alto de la mano en pixeles
    ALTO_ANTEBRAZO: number = 3 * this.ANCHO_MAXIMO_CANVAS / 10;//Alto del antebrazo en pixeles
    ALTO_BRAZO: number = 3 * this.ANCHO_MAXIMO_CANVAS / 10;//Alto del brazo en pixeles
}
