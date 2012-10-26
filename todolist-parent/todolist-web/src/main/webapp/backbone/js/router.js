var app = app || {};

define([
        'jquery','underscore','backbone','views/header','views/home','views/login','views/reassigntask',
        'views/userregistration','views/taskslist','views/newtask','views/taskdetail','views/contact',
        'models/task'
        ], function($,_,Backbone){
	
		app.AppRouter = Backbone.Router.extend({
			routes:{
				"" : "login",
				"contact":"contact",
				"userregistration":"userregistration",
				"taskslist":"taskslist",
				"taskdetail/:id":"taskdetail",
				"reassigntask/:id": "reassigntask",
				"employees/:id":"employeeDetails",
				"newtask":"newtask",
		        "token/:id/:tokenid":"confirm",
		        "editTask/:id":"editTask"
			},
			
			initialize:function () {
				this.headerView = new app.HeaderView();
				this.headerView.render();
				
				// Close the search dropdown on click anywhere in the UI
				$('body').click(function () {
					$('.dropdown').removeClass("open");
				}); 
			},
			
			home:function () {
				// Since the home view never changes, we instantiate it and render it only once
				if (!this.homeView) {
					this.homeView = new app.HomeView();
				}
				this.homeView.render();
			},
			
			contact:function () {
				if (!this.contactView) {
					this.contactView = new app.ContactView();
				}
				this.contactView.render();
			},
			
			login: function() {
				new app.LoginView().render();
			},
			
			reassigntask: function(id) {
				var task = new app.Task({id:id});
				task.fetch({
					success:function (data) {
						// Note that we could also 'recycle' the same instance of EmployeeFullView
						// instead of creating new instances
						new app.ReassignTaskView({model:data}).render();
					}
				});
				
			},
			
			taskslist: function() {
				new app.TasksListView().render();
			},
			userregistration: function() {
				new app.UserRegistrationView().render();
			},
			taskdetail: function(id) {
		     	var task = new app.Task({id:id});
		     	task.fetch({
		             success:function (data) {
		                 // Note that we could also 'recycle' the same instance of EmployeeFullView
		                 // instead of creating new instances
		             	new app.TaskDetailView({model:data}).render();
		             }
		         });
				         
			},
		    editTask: function(id) {
		    	var task = new app.Task({id:id});
		     	task.fetch({
		             success:function (data) {
		                 // Note that we could also 'recycle' the same instance of EmployeeFullView
		                 // instead of creating new instances
		             	new app.NewTaskView({model:data}).render();
		             }
		         });
			         
			},
			newtask: function(){
				new app.NewTaskView().render();
			},
			 confirm: function(id, tokenid){
		    	 var url = '../rest/session';
		         console.log('checking email confirmation... ');
		      	 $.ajax({
			            url:url,
			            type:'PUT',
			            dataType:"json",
			            data: { email: id, token: tokenid },
			            success:function (data) {
			                console.log(["Login request details: ", data]);
			           	 	new app.LoginView({}).render();
			           	 	$('#errorLogin').hide();
			           	 	$('#confirmation').text("Your email has been verified.").show();
			            },
			            error: function(jqXHR, textStatus, errorThrown) {
			            	$('#errorLogin').text("Error").show();
			            	$('#confirmation').text("Your email has been verified.").hide();
			            }
			        });
		    }
		});
		
		var initialize = function(){
			//app = new AppRouter();
			app.application = new app.AppRouter();
			Backbone.history.start();
		};
		
		return {
			initialize: initialize
		};
		
});