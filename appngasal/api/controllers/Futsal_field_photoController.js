/**
 * Futsal_field_photoController
 *
 * @description :: Server-side logic for managing futsal_field_photoes
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
        Futsal_field_photo.create(req.params.all(),function futsalFieldPhotoCreated(err, futsalFieldPhoto){
            if(err){
                console.log(err)
            
        }
        res.json(201,futsalFieldPhoto);
        
        });
    },
    edit: function(req, res, next){
        Futsal_field_photo.findOne(req.param('id'), function foundfutsalFieldPhoto(err,futsalFieldPhoto){
            if(err) return next(err);
            if(!futsalFieldPhoto) return next('Futsal Field Photo doesn\'t exist.');
            // res.view({
            //     booking: booking
            // });
        });
    },

    update: function(req, res, next){
        Futsal_field_photo.update(req.param('id'),req.params.all(), function futsalFieldPhotoUpdated(err){
            if(err){
                return res.redirect('/Futsal_field_photo/' + req.param('id'));
            }
            res.json(201,futsalFieldPhoto);
        });
    },
    delete: function(req, res, next){
        Futsal_field_photo.findOne(req.param('id'), function foundfutsalFieldPhoto(err,futsalFieldPhoto){
            if(err) return next(err);

            if(!futsalFieldPhoto) return next('Futsal field Photo doesn\'t exist.');

            Futsal_field_photo.destroy(req.param('id'), function futsalFieldPhotoDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,futsalFieldPhoto);
        });
    }
};

