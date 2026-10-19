export function crearFooter() {
    const anio = new Date().getFullYear();
    return `
        <footer class="footer">
            <div class="footer-contenido">
                <div class="footer-marca">
                    <h2 class="footer-logo">Menu<span>QR</span></h2>
                    <p class="footer-lema">Tu menú, en un código</p>
                </div>

                <div class="footer-columna">
                    <h3>Contacto</h3>
                    <p>Calle Gran Vía, 12</p>
                    <p>18001 Granada, España</p>
                    <p>+34 958 00 00 00</p>
                    <p>hola@menuqr.es</p>
                </div>

                <div class="footer-columna">
                    <h3>Horario</h3>
                    <p>Lunes a Viernes: 13:00 – 23:30</p>
                    <p>Sábado y Domingo: 12:00 – 00:00</p>
                </div>

                <div class="footer-columna">
                    <h3>Síguenos</h3>
                    <div class="footer-redes">
                        <a href="https://www.instagram.com/?hl=es" aria-label="Instagram">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/a/a5/Instagram_icon.png" alt="Instagram" width="28" height="28">
                        </a>
                        <a href="https://www.facebook.com/?locale=es_ES" aria-label="Facebook">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/0/05/Facebook_Logo_%282019%29.png" alt="Facebook" width="28" height="28">
                        </a>
                        <a href="https://x.com/?lang=es" aria-label="X">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/5/53/X_logo_2023_original.svg" alt="X" width="28" height="28">
                        </a>
                    </div>
                </div>
            </div>

            <div class="footer-base">
                <p>© ${anio} MenuQR · Todos los derechos reservados</p>
            </div>
        </footer>
    `;
}