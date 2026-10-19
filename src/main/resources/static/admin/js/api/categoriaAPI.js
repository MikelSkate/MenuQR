import { fetchApi } from '../utils/apiUtils.js';
 
export const CategoriaAPI = {
    obtenerTodos: () => fetchApi('GET', '/categoria'),
 
    obtenerPorId: (id) => fetchApi('GET', `/categoria/${id}`),
 
    crear: (categoria) => fetchApi('POST', '/categoria', categoria),
 
    actualizar: (id, categoria) => fetchApi('PUT', `/categoria/${id}`, categoria),
 
    eliminar: (id) => fetchApi('DELETE', `/categoria/${id}`)
};