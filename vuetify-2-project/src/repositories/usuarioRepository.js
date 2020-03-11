import Repository from "./Repository";

const resource = "/usuarios";

export default {
    getByAutenticacao() {
        return Repository.get(`${resource}`);
    },

    getAll() {
      return Repository.get(`${resource}`)
    },
    addUser(user){
      return Repository.post(`${resource}`, user)
    },
    removeUser(id){      
      return Repository.delete("/usuarios" + "/"+id)
    },
    updateUser({user,id}){
      return Repository.put(`${resource}/${id}`,user)
    }
}
