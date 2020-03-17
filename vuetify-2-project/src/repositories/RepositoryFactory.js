import UsuarioRepository from "./usuarioRepository";
import Courses from "./courseRepository"
const repositories = {
    usuario: UsuarioRepository,
    courses: Courses
};

export const RepositoryFactory = {
    get: name => repositories[name]
};
