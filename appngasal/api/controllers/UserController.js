/**
 * UserController
 *
 * @description :: Server-side logic for managing users
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
        User.create(req.params.all(),function userCreated(err, user){
            if(err){
                console.log(err)
            
        }
        res.json(201,user);
        //res.send('oke')
        
        });
    },
    edit: function(req, res, next){
        User.findOne(req.param('id'), function foundUsers(err,user){
            if(err) return next(err);
            if(!user) return next('User doesn\'t exist.');
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
            res.json(201,user);
        });
    },
    delete: function(req, res, next){
        User.findOne(req.param('id'), function foundUsers(err,user){
            if(err) return next(err);

            if(!user) return next('User doesn\'t exist.');

            User.destroy(req.param('id'), function userDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,user);
        });
    }
       
    
      
};

