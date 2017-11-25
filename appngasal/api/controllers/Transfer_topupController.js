/**
 * Transfer_topupController
 *
 * @description :: Server-side logic for managing transfer_topups
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
        Transfer_topup.create(req.params.all(),function transferTopupCreated(err, transferTopup){
            if(err){
                console.log(err)
            
        }
        res.json(201,transferTopup);
        //res.send('oke')
        
        });
    },
    edit: function(req, res, next){
        Transfer_topup.findOne(req.param('id'), function foundtransferTopup(err,transferTopup){
            if(err) return next(err);
            if(!transferTopup) return next('transferTopup doesn\'t exist.');
            // res.view({
            //     user: user
            // });
        });
    },

    update: function(req, res, next){
        Transfer_topup.update(req.param('id'),req.params.all(), function transferTopupUpdated(err){
            if(err){
                return res.redirect('/transfer_topup/' + req.param('id'));
            }
            res.json(201,transferTopup);
        });
    },
    delete: function(req, res, next){
        Transfer_topup.findOne(req.param('id'), function foundtransferTopup(err,transferTopup){
            if(err) return next(err);

            if(!transferTopup) return next('transferTopup doesn\'t exist.');

            Transfer_topup.destroy(req.param('id'), function transferTopupDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,transferTopup);
        });
    }
    
};

