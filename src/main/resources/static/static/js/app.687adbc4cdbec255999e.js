webpackJsonp([1],{"2BUe":function(t,e){},"6y12":function(t,e){},G1Oe:function(t,e){},KEOf:function(t,e){},KhTB:function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("VCXJ"),i={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]};var s=a("X4nt")({name:"app"},i,!1,function(t){a("6y12")},null,null).exports,o=a("zO6J"),c=a("wfor"),r={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-container",{staticClass:"login-bg"},[a("el-main",{staticStyle:{"min-height":"calc(100vh)"}},[a("el-row",{staticClass:"row-bg",attrs:{type:"flex",justify:"center"}},[a("el-col",{attrs:{span:24}},[a("div",{staticClass:"user-info"},[a("div",{staticClass:"login-method"},[a("div",{staticClass:"logo"}),t._v(" "),a("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{name:"first"}},[a("span",{attrs:{slot:"label"},slot:"label"},[a("i",{staticClass:"login-icon face-recognition"}),t._v(" 人脸识别")]),t._v(" "),a("div",{staticClass:"login-content"},[a("div",{staticClass:"canvas"},[a("div",{staticClass:"canvas-content"},[a("video",{ref:"video",staticStyle:{"z-index":"1"},attrs:{id:"video",width:"316",height:"266"}}),t._v(" "),a("canvas",{directives:[{name:"show",rawName:"v-show",value:t.isShowImg,expression:"isShowImg"}],ref:"canvas",staticStyle:{"z-index":"2"},attrs:{id:"canvas"}})])]),t._v(" "),a("div",{staticClass:"submit-div",on:{click:t.photo}},[a("span",[a("i",{staticClass:"login-icon take-photo"}),t._v(" 拍摄")])])])]),t._v(" "),a("el-tab-pane",{attrs:{name:"second"}},[a("span",{attrs:{slot:"label"},slot:"label"},[a("i",{staticClass:"login-icon user-recognition"}),t._v(" 帐号登录")]),t._v(" "),a("div",{staticClass:"form"},[a("div",[a("span",{staticClass:"user"}),t._v(" "),a("input",{attrs:{type:"text"}})]),t._v(" "),a("div",[a("span",{staticClass:"pwd"}),t._v(" "),a("input",{attrs:{type:"password"}})]),t._v(" "),a("span",{staticClass:"submit-span"},[t._v("登录 "),a("i")])])])],1)],1)])])],1)],1)],1)},staticRenderFns:[]};var l=function(t){a("2BUe")},u=a("X4nt")(c.a,r,!1,l,"data-v-4e4bef4a",null).exports;n.default.use(o.a);var d=new o.a({routes:[{path:"/",name:"登录",component:u}]}),f=a("mwE6"),v=a.n(f),p=(a("cU5d"),a("bTfe"),a("swv6"),a("e5CY"),a("Wn0u"),a("tra3"),a("GwpK"),a("2sCs")),m=a.n(p);a("fmhJ"),a("w0V6"),a("upRW"),a("OBr0"),a("jd3b"),a("G1Oe"),a("KhTB"),a("KEOf"),a("e4HC"),a("jUbk"),a("fvRm");n.default.config.productionTip=!1,n.default.use(v.a),n.default.use(m.a),new n.default({el:"#app",router:d,template:"<App/>",components:{App:s}})},OBr0:function(t,e){},Wn0u:function(t,e){},bTfe:function(t,e){},cU5d:function(t,e){},e4HC:function(t,e){},e5CY:function(t,e){},fmhJ:function(t,e){},fvRm:function(t,e){},jUbk:function(t,e){},jd3b:function(t,e){},swv6:function(t,e){},upRW:function(t,e){},w0V6:function(t,e){},wfor:function(t,e,a){"use strict";(function(t){e.a={name:"login",data:function(){return{activeName:"first",form:{username:"",password:""},isShowImg:!1,dataURL:""}},mounted:function(){this.initPhoto()},methods:{handleClick:function(t,e){console.log(t,e)},initPhoto:function(){var t=this;navigator.mediaDevices.getUserMedia({audio:!1,video:{width:316,height:266}}).then(function(e){var a=t.$refs.video;a.src=window.URL.createObjectURL(e),a.onloadedmetadata=function(t){a.play()},t.video=a,t.track=e.getTracks()[0]}).catch(function(e){console.log("err.message"+e.name),t.$message.error("未检测到摄像头!")})},photo:function(){var e,a=this,n=a.$refs.canvas,i=n.getContext("2d");n.width=316,n.height=266,i.drawImage(a.video,0,0,316,266),e=n.toDataURL("image/png"),a.dataURL=e,a.isShowImg=!0,t.ajax({type:"post",url:"http://172.18.0.123:8080/image/compare",data:{imageUrl:e},xhrFields:{withCredentials:!0},crossDomain:!0,success:function(t){console.log(t)}}),setTimeout(function(){a.isShowImg=!1},5e3)}}}}).call(e,a("tra3"))}},["NHnr"]);
//# sourceMappingURL=app.687adbc4cdbec255999e.js.map