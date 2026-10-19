import { crearNavbar } from '../componentes/navbar.js';
import { MesaAPI } from '../api/mesaAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Comprobar si estamos editando o creando
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
 
    let mesa = { numero: '', mesaQr: '' };
    let titulo = 'Nueva mesa';
 
    if (id) {
        mesa = await MesaAPI.obtenerPorId(id);
        titulo = 'Editar mesa';
    }
 
    // Inyectar formulario en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>${titulo}</h1>
            <form id="formulario">
                <div class="campo">
                    <label for="numero">Numero</label>
                    <input type="number" id="numero" value="${mesa.numero}" required>

                    <label for="mesaQr">MesaQr</label>
                    <input type="text" id="mesaQr" value="${mesa.mesaQr}" required>
                </div>
                <button type="submit">${id ? 'Guardar cambios' : 'Crear mesa'}</button>
                <a href="mesas.html">Cancelar</a>
            </form>
        </div>
    `;
 
    // Manejar envio del formulario
    document.getElementById('formulario').addEventListener('submit', async (e) => {
        e.preventDefault();
 
        const datos = {
            numero: document.getElementById('numero').value,
            mesaQr: document.getElementById('mesaQr').value
        };
 
        if (id) {
            await MesaAPI.actualizar(id, datos);
        } else {
            await MesaAPI.crear(datos);
        }
 
        window.location.href = 'mesas.html';
    });
});