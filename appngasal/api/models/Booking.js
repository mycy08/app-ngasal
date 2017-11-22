/**
 * Booking.js
 *
 * @description :: TODO: You might write a short summary of how this model works and what it represents here.
 * @docs        :: http://sailsjs.org/documentation/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
      user_id:{
        type:'string'
      },
      futsal_field_id:{
        type:'string'
      },
      time:{
        type:'number'
      },
      date:{
        type:'number'
      },
      duration:{
        type:'number'
      },
      status:{
        type:'string'
      }
  },
  connection:'appngasaldb'
};

