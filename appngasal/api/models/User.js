/**
 * User.js
 *
 * @description :: TODO: You might write a short summary of how this model works and what it represents here.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    email:{
      type:'string',
      email: true,
      required:true,
      unique:true
    },
    password:{
      type:'string',
      required:true
    },
    name:{
      type:'string',
      required:true
    },
    confirmed:{
      type:'string'
    },
    balance:{
      type:'integer'
    },
    birth_date:{
      type:'string'

    },
    photo:{
      type:'string'

    },
    phone_number:{
      type:'string'

    },
    chart:{
      type:'string'

    },
    toJSON: function(){
      var obj = this.toObject();
      delete obj.password;
      delete obj.csrf;
      return obj;

    
    }
    
  },
  connection:'appngasaldb'
};
