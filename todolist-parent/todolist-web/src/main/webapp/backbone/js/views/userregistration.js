window.UserRegistrationView = Backbone.View.extend({

    initialize:function () {
        console.log('Initializing User registration View');
        this.template = _.template(tpl.get('userregistration'));
    },

    events: {
        "click #newaccount": "newaccount",
        "click #cancel": "cancel"
        
    },

    render:function () {
    	 $(this.el).html(this.template());
        return this;
    },
	
	cancel:function(event){

	},
    
    newaccount:function(event) {
        event.preventDefault(); // Don't let this button submit the form
        $('.alert-error').hide(); // Hide any errors on a new submit
        var url = '../rest/users';
        console.log('Loggin in... ');
        var formValues = {
            email: $('#inputEmail').val(),
            password: $('#inputPassword').val()
        };

        $.ajax({
            url:url,
            type:'POST',
            dataType:"json",
            data: { email: $('#inputEmail').val(), password: $('#inputPassword').val() },
            success:function (data) {
                console.log(["Users request details: ", data]);
               
                if(data.error) {  // If there is an error, show the error messages
                    $('.alert-error').text(data.error.text).show();
                }
                else { // If not, send them back to the home page
                    window.location.replace('#login'); // todo: tasklist
                }
            }
        });
    }
    
});