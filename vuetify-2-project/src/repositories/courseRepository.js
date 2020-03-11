import Repository from "./Repository";

const resource = "/course";

export default {
    getByAutenticacao(id) {
        return Repository.get(`${resource}/${id}`);
    },

    getAll() {
      return Repository.get(`${resource}`)
    },
    addUser(user){
      return Repository.post(`${resource}`, user)
    },
    removeUser(id){
      return Repository.delete(`${resource}`,id)
    },
    updateUser(){
      return Repository.put(`${resource}/${id}`)
    }
}
