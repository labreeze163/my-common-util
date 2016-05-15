
// fruit catalog name
var IMPORT_CATALOG_NAME = "精品水果";
var NATION_CATALOG_NAME = "国产水果";
var LOCAL_CATALOG_NAME ="本地水果";
var HOME_PAGE_LINK = "首页";

$(document).ready(function() { 
	
	bindCatalogUrl();
});


var bindCatalogUrl = function() {
	$('.fruit-catalog').click(function() {
		var catalogKey = $(this).children().get(0).text;
		$('.row').hide();
		
		if(IMPORT_CATALOG_NAME == catalogKey) $('#importListContaner').show(2000);
		if(NATION_CATALOG_NAME == catalogKey) $('#nationListContainer').show(2000);
		if(HOME_PAGE_LINK == catalogKey) $('.row').show(2000);
	});
	
}