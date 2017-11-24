/**
 * BookingController
 *
 * @description :: Server-side logic for managing bookings
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {
	create: function(req, res, next){
        Booking.create(req.params.all(),function bookingCreated(err, booking){
            if(err){
                console.log(err)
            
        }
        res.json(201,booking);
        
        });
    },
    edit: function(req, res, next){
        Booking.findOne(req.param('id'), function foundBookings(err,booking){
            if(err) return next(err);
            if(!booking) return next('Booking doesn\'t exist.');
            // res.view({
            //     booking: booking
            // });
        });
    },

    update: function(req, res, next){
        Booking.update(req.param('id'),req.params.all(), function bookingUpdated(err){
            if(err){
                return res.redirect('/booking/' + req.param('id'));
            }
            res.json(201,booking);
        });
    },
    delete: function(req, res, next){
        Booking.findOne(req.param('id'), function foundBookings(err,booking){
            if(err) return next(err);

            if(!booking) return next('booking doesn\'t exist.');

            Booking.destroy(req.param('id'), function bookingDestroyed(err){
                if(err) return next(err);
            });
            res.json(202,booking);
        });
    }
};

