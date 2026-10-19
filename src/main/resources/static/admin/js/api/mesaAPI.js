import { fetchApi } from '../utils/apiUtils.js';
 
export const MesaAPI = {
    obtenerTodos: () => fetchApi('GET', '/mesa'),
 
    obtenerPorId: (id) => fetchApi('GET', `/mesa/${id}`),
 
    crear: (mesa) => fetchApi('POST', '/mesa', mesa),
 
    actualizar: (id, mesa) => fetchApi('PUT', `/mesa/${id}`, mesa),
 
    eliminar: (id) => fetchApi('DELETE', `/mesa/${id}`)
};