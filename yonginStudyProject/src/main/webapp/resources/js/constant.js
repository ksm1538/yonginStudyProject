var dialog = new ax5.ui.dialog();
var mask = new ax5.ui.mask();
var sToast = new ax5.ui.toast();
var dToast = new ax5.ui.toast();
var COMMON_SUCCESS = "success";
var COMMON_FAIL = "fail";

dialog.setConfig({
    title: "YonginStudy",
    onStateChanged: function () {
        if (this.state === "open") {
        	mask.open();
        }
        else if (this.state === "close") {
        	mask.close();
        }
    }
});

/*toast 설정 - danger*/
dToast.setConfig({
	icon: '<i class="fa fa-bell"></i>',
	theme:"danger",
	containerPosition: "top-left",
});

/*toast 설정 - success*/
sToast.setConfig({
    icon: '<i class="fa fa-bell"></i>',
	theme:"success",
	containerPosition: "top-left"
});