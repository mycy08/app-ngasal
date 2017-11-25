/**
 * Futsal_field_photo.js
 *
 * @description :: TODO: You might write a short summary of how this model works and what it represents here.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
      futsal_field_id:{
        type:'string'
      },
      futsal_photo:{
        type:'string'
      },
      futsal_photo1:{
        type:'string'
      },
      futsal_photo2:{
        type:'string'
      },
      futsal_photo3:{
        type:'string'
      }
  },
  connection:'appngasaldb'
};

