import React from "react";



export default class AddBookPopup extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidMount() {
	    this.events();
	}

	events(){
		/*prevent click on modal to propagate to window and close the modal */
		(function(){
			document.querySelector("#addBookPopup .modal-dialog").addEventListener('click',(e)=>{
				debugger;
				e.preventDefault();
				e.stopPropagation();
			});
		})();
	}

	modalClass(){
		//debugger;
		var modalClass = "modal";
		if(this.props.popupDisplay=="block"){
			modalClass += " show";
		}
		return modalClass;
	}

	render(){
		//debugger;
		const modalClass = this.modalClass();

		return (
			<div className="container">
		  	
			  	<div className={modalClass} id="addBookPopup" role="dialog">
				    <div className="modal-dialog">
				        
				        <div className="modal-content">
				        	<div className="modal-header">
					            <h2 className="modal-title">ADD BOOK</h2>
				       	    </div>

				        	<div className="modal-body">
				            	<input type="text" id="name" className="form-control margin-top-10" name="name" placeholder="Name"/>
				            	<input type="text" id="authorName" className="form-control margin-top-10" name="authorName" placeholder="Author Name"/>
				            	<div id="categoryDropdown" className="margin-top-10">
				                	<input type="text" id="category" className="form-control" name="category" placeholder="Category"/>
				                	<div id="categoriesList">
					                    <div className="category" ng-click="selectCategory()" ng-repeat="category in categories">
					                        <span></span>
					                    </div>
				                	</div>
				            	</div>
					            <div id="subcategoryDropdown" className="margin-top-10">
					                <input type="text" id="subcategory" className="form-control" name="subcategory" placeholder="subcategory"/>
					                <div id="subcategoriesList">
					                    <div className="subcategory" ng-click="selectSubCategory()" ng-repeat="subcategory in subcategories[selectedCategory]">
					                        <span></span>
					                    </div>
					                </div>
					            </div>
					            <input type="number" id="pages" className="form-control margin-top-10" name="pages" placeholder="Pages"/>
					            <input type="text" id="image" className="form-control margin-top-10" name="image" placeholder="Add image"/>
					            <input type="text" id="available" className="form-control margin-top-10 hidden" name="available" value="1"/>
					            <div className="margin-top-10"><input type="checkbox" ng-click="toggleBuyChecked()" id="buy"  name="buyout"/> Buyout <input type="text" id="buyAmount" className="form-control margin-top-10" ng-show="buyChecked" name="buyAmount" placeholder="Enter the buyout amount"/></div>

					            <div className="margin-top-10"><input type="checkbox" ng-click="toggleRentChecked()" id="rent"  name="rent"/> Rent <input type="text" id="rentAmount" className="form-control margin-top-10" ng-show="rentChecked" name="rentAmount" placeholder="Enter the rent amount"/></div>


				            	<button type="button" id="addBookButton" className="btn btn-lg btn-danger btn-block margin-top-20">ADD</button>
				        </div>
				        <div className="modal-footer">
				            <div id="addBookMessageContainer" className="center-align"></div>
				        </div>
				      </div>
				    </div>
			  	</div>
			  
			</div>
		);
	}
}