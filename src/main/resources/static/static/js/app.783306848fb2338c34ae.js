webpackJsonp([1],{"6y12":function(t,e){},BG2n:function(t,e){},"C1/j":function(t,e){},Dgog:function(t,e){},DxFl:function(t,e){},G1Oe:function(t,e){},KEOf:function(t,e){},KhTB:function(t,e){},NHnr:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=n("VCXJ"),i={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]};var s=n("X4nt")({name:"app"},i,!1,function(t){n("6y12")},null,null).exports,o=n("zO6J"),c=n("wfor"),r={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-container",{staticClass:"login-bg"},[n("el-main",{staticStyle:{"min-height":"calc(100vh)"}},[n("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"center"}},[n("el-col",{attrs:{span:24}},[n("div",{staticClass:"user-info"},[n("div",{staticClass:"login-method"},[n("div",{staticClass:"logo"}),t._v(" "),n("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[n("el-tab-pane",{attrs:{name:"first"}},[n("span",{attrs:{slot:"label"},slot:"label"},[n("i",{staticClass:"login-icon face-recognition"}),t._v(" 人脸识别")]),t._v(" "),n("div",{staticClass:"login-content"},[n("div",{staticClass:"canvas"},[n("div",{staticClass:"canvas-content"},[n("video",{ref:"video",staticStyle:{"z-index":"1"},attrs:{id:"video",width:"316",height:"266"}}),t._v(" "),n("canvas",{directives:[{name:"show",rawName:"v-show",value:t.isShowImg,expression:"isShowImg"}],ref:"canvas",staticStyle:{"z-index":"2"},attrs:{id:"canvas"}})])]),t._v(" "),n("div",{staticClass:"submit-div",on:{click:t.photo}},[n("span",[n("i",{staticClass:"login-icon take-photo"}),t._v(" 拍摄")])])])]),t._v(" "),n("el-tab-pane",{attrs:{name:"second"}},[n("span",{attrs:{slot:"label"},slot:"label"},[n("i",{staticClass:"login-icon user-recognition"}),t._v(" 帐号登录")]),t._v(" "),n("div",{staticClass:"form"},[n("div",[n("span",{staticClass:"user"}),t._v(" "),n("input",{attrs:{type:"text"}})]),t._v(" "),n("div",[n("span",{staticClass:"pwd"}),t._v(" "),n("input",{attrs:{type:"password"}})]),t._v(" "),n("span",{staticClass:"submit-span",on:{click:function(e){t.$router.push({path:"/Home/Index"})}}},[t._v("登录 "),n("i")])])])],1)],1)])])],1)],1)],1)},staticRenderFns:[]};var l=function(t){n("jVEL")},d=n("X4nt")(c.a,r,!1,l,"data-v-2ba57dbf",null).exports,u={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-container",[n("el-aside",{staticClass:"nav-bg",staticStyle:{width:"auto"}},[n("el-menu",{staticClass:"el-menu-vertical-demo",attrs:{"default-active":"1-1","unique-opened":!0,"text-color":t.textColor,collapse:t.isCollapse,"active-text-color":t.activeColor}},[n("el-submenu",{attrs:{index:"1"}},[n("template",{slot:"title"},[n("i",{staticClass:"el-icon-location"}),t._v(" "),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("导航一")])]),t._v(" "),n("el-menu-item",{staticStyle:{padding:"0","text-align":"center"},attrs:{index:"1-1"}},[t._v("选项1")]),t._v(" "),n("el-menu-item",{staticStyle:{padding:"0","text-align":"center"},attrs:{index:"1-2"}},[t._v("选项1")]),t._v(" "),n("el-menu-item",{staticStyle:{padding:"0","text-align":"center"},attrs:{index:"1-3"}},[t._v("选项1")])],2),t._v(" "),n("el-submenu",{attrs:{index:"2"}},[n("template",{slot:"title"},[n("i",{staticClass:"el-icon-location"}),t._v(" "),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("导航2")])]),t._v(" "),n("el-menu-item",{staticStyle:{padding:"0","text-align":"center"},attrs:{index:"2-1"}},[t._v("选项1")])],2)],1)],1),t._v(" "),n("el-main",[t._v("Main")])],1)},staticRenderFns:[]};var m=n("X4nt")({name:"home",data:function(){return{isCollapse:!1,backgroundColor:"#263638",textColor:"#97a3aa",activeColor:"#ffffff",menuLists:[{id:1,name:"监控管理",icon:"icon wb-desktop",weight:1,children:[{id:2,name:"现场情况监控",icon:"",weight:1}]},{id:2,name:"系统设置",icon:"icon wb-desktop",weight:2,children:[{id:3,name:"人员管理",icon:"",weight:1},{id:4,name:"通道管理",icon:"",weight:2},{id:5,name:"设备管理",icon:"",weight:3},{id:6,name:"角色管理",icon:"",weight:4},{id:7,name:"权限管理",icon:"",weight:5},{id:8,name:"部门管理",icon:"",weight:6}]}]}}},u,!1,function(t){n("BG2n")},"data-v-ad58ebce",null).exports,v={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-container",{staticStyle:{"min-height":"calc(100vh)"}},[n("el-header",{staticClass:"header-title"},[n("div",{staticClass:"logo"}),t._v(" "),n("a",{staticClass:"user-set"},[n("i"),t._v(" 个人设置")]),t._v(" "),n("a",{staticClass:"user-login-out"},[n("i"),t._v("用户名")])]),t._v(" "),n("router-view")],1)},staticRenderFns:[]};var f=n("X4nt")({name:"headers"},v,!1,function(t){n("gyQR")},"data-v-6bcfd2bb",null).exports,p={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var h=n("X4nt")({name:"situations-monitoring"},p,!1,function(t){n("yfdv")},"data-v-ce9dfcc6",null).exports,g={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var _=n("X4nt")({name:"aisle-manage"},g,!1,function(t){n("xLSj")},"data-v-1ab39667",null).exports,x={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var w=n("X4nt")({name:"department-manage"},x,!1,function(t){n("Vdm/")},"data-v-f1d5b9da",null).exports,b={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var C=n("X4nt")({name:"device-manage"},b,!1,function(t){n("zsgx")},"data-v-3792c6da",null).exports,S={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var y=n("X4nt")({name:"jurisdiction-manage"},S,!1,function(t){n("C1/j")},"data-v-6c7dba33",null).exports,R={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var j=n("X4nt")({name:"parameters"},R,!1,function(t){n("DxFl")},"data-v-935ee888",null).exports,E={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var $=n("X4nt")({name:"role-manage"},E,!1,function(t){n("Dgog")},"data-v-44becc13",null).exports,k={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]};var F=n("X4nt")({name:"user-manage"},k,!1,function(t){n("ebZj")},"data-v-6e988e1a",null).exports;a.default.use(o.a);var U=new o.a({routes:[{path:"/",name:"登录",component:d},{path:"/Home",name:"公用头部",component:f,children:[{path:"Index",name:"首页",component:m,children:[{path:"MonitoringManage/Situations",name:"现场监控",component:h},{path:"SystemSetting/Aisle",name:"通道号管理",component:_},{path:"SystemSetting/Department",name:"部门管理",component:w},{path:"SystemSetting/Device",name:"设备管理",component:C},{path:"SystemSetting/Jurisdiction",name:"权限管理",component:y},{path:"SystemSetting/Parameters",name:"一般参数",component:j},{path:"SystemSetting/Role",name:"角色管理",component:$},{path:"SystemSetting/User",name:"人员管理",component:F}]}]}]}),X=n("mwE6"),I=n.n(X);n("cU5d"),n("tra3"),n("GwpK"),n("fmhJ"),n("w0V6"),n("upRW"),n("OBr0"),n("jd3b"),n("G1Oe"),n("KhTB"),n("KEOf"),n("e4HC"),n("jUbk"),n("fvRm"),n("bTfe");a.default.config.productionTip=!1,a.default.use(I.a),new a.default({el:"#app",router:U,template:"<App/>",components:{App:s}})},OBr0:function(t,e){},V5IQ:function(t,e,n){"use strict";var a={Url:".."},i=n("X4nt")(a,null,!1,null,null,null);e.a=i.exports},"Vdm/":function(t,e){},bTfe:function(t,e){},cU5d:function(t,e){},e4HC:function(t,e){},ebZj:function(t,e){},fmhJ:function(t,e){},fvRm:function(t,e){},gyQR:function(t,e){},jUbk:function(t,e){},jVEL:function(t,e){},jd3b:function(t,e){},upRW:function(t,e){},w0V6:function(t,e){},wfor:function(t,e,n){"use strict";(function(t){var a=n("V5IQ");e.a={name:"login",data:function(){return{activeName:"first",form:{username:"",password:""},isShowImg:!1,dataURL:""}},mounted:function(){this.initPhoto()},methods:{handleClick:function(t,e){console.log(t,e)},initPhoto:function(){var t=this;navigator.mediaDevices.getUserMedia({audio:!1,video:{width:316,height:266}}).then(function(e){var n=t.$refs.video;n.src=window.URL.createObjectURL(e),n.onloadedmetadata=function(t){n.play()},t.video=n,t.track=e.getTracks()[0]}).catch(function(e){console.log("err.message"+e.name),t.$message.error("未检测到摄像头!")})},photo:function(){var e,n=this,i=n.$refs.canvas,s=i.getContext("2d");i.width=316,i.height=266,s.drawImage(n.video,0,0,316,266),e=i.toDataURL("image/png"),n.dataURL=e,n.isShowImg=!0,t.ajax({type:"post",url:a.a.Url+"/image/compare",data:{imageUrl:e},success:function(t){console.log(t)}}),setTimeout(function(){n.isShowImg=!1},5e3)}}}}).call(e,n("tra3"))},xLSj:function(t,e){},yfdv:function(t,e){},zsgx:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.783306848fb2338c34ae.js.map