import Repository from "./Repository";

const resource = "/curso"; 

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
      return Repository.delete(`${resource}`,id)
    },
    updateUser(){
      return Repository.put(`${resource}/${id}`)
    }
}
