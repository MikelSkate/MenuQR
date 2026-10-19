import { fetchApi } from '../utils/apiUtils.js';
 
export const CategoriaAPI = {
    obtenerTodos: () => fetchApi('GET', '/categoria')
};