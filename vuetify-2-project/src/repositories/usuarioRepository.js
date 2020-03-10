import Repository from "./Repository";

const resource = "/usuarios";

export default {
    getByAutenticacao() {
        return Repository.get(`${resource}/usuario`);
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
