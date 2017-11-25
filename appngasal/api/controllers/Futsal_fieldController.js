/**
 * Futsal_fieldController
 *
 * @description :: Server-side logic for managing futsal_fields
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
        Futsal_field.create(req.params.all(),function futsalFieldCreated(err, futsalField){
            if(err){
                console.log(err)
            
        }
        res.json(201,futsalField);
        
        });
    },
    edit: function(req, res, next){
        Futsal_field.findOne(req.param('id'), function foundFutsalField(err,futsalField){
            if(err) return next(err);
            if(!booking) return next('Futsal Field doesn\'t exist.');
            // res.view({
            //     booking: booking
            // });
        });
    },

    update: function(req, res, next){
        Futsal_field.update(req.param('id'),req.params.all(), function futsalFieldUpdated(err){
            if(err){
                return res.redirect('/Futsal_field/' + req.param('id'));
            }
            res.json(201,futsalField);
        });
    },
    delete: function(req, res, next){
        Futsal_field.findOne(req.param('id'), function foundFutsalField(err,futsalField){
            if(err) return next(err);

            if(!futsalField) return next('Futsal field doesn\'t exist.');

            Futsal_field.destroy(req.param('id'), function futsalFieldDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,futsalField);
        });
    }
};

