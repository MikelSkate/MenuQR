import { fetchApi } from '../utils/apiUtils.js';
 
export const lineaPedidoAPI = {
    obtenerTodos: () => fetchApi('GET', '/lineaPedido'),
 
    obtenerPorId: (id) => fetchApi('GET', `/lineaPedido/${id}`),
 
    crear: (lineaPedido) => fetchApi('POST', '/lineaPedido', lineaPedido),
 
    actualizar: (id, lineaPedido) => fetchApi('PUT', `/lineaPedido/${id}`, lineaPedido),
 
    eliminar: (id) => fetchApi('DELETE', `/lineaPedido/${id}`)
};