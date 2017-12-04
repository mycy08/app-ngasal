/**
 * UserController
 *
 * @description :: Server-side logic for managing users
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
       var email = req.param('email')
        User.findOne({email:email}).exec(function(err, user){
            if(!user){
                User.create(req.params.all(),function userCreated(err, user){
                    if(err){
                        console.log(err)
                    
                }       
                return res.send(201,'Register Succesfull')
                
                });
             
            }
            else{
                return res.send(400,'Email is already registered')
            }
        })
        
    },
    edit: function(req, res, next){
        User.findOne(req.param('id'), function foundUsers(err,user){
            if(err) return next(err);
            if(!user) return res.send(404,'User doesn\'t exist.');
            // res.view({
            //     user: user
            // });
        });
    },

    update: function(req, res, next){
        User.update(req.param('id'),req.params.all(), function userUpdated(err){
            if(err){
                return res.redirect('/user/' + req.param('id'));
            }
            res.send(200,'User has been successfully changed');
        });
    },
    delete: function(req, res, next){
        User.findOne(req.param('id'), function foundUsers(err,user){
            if(err) return next(err);

            if(!user) return res.send(404,'User doesn\'t exist.');

            User.destroy(req.param('id'), function userDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,user);
        });
    },
    login : function(req,res){
        
    }
       
    
      
};

