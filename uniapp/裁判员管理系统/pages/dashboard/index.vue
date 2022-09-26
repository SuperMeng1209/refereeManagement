<template>
	<view>
		<view v-if="page == 0">
			<!-- 首页 -->
			<i-dashboard></i-dashboard>
		</view>
		<view v-if="page == 1">
			<!-- 注册 -->
			<i-register></i-register>
		</view>
		<view v-if="page == 2">
			<i-years-register></i-years-register>
		</view>
		<view v-if="page == 3">
			<i-train></i-train>
		</view>
		<view v-if="page == 4">
			<!-- 赛事新闻 -->
			<i-report></i-report>
		</view>
		<view v-if="page == 5">
			<i-me @gotosaishi="gotosaishi"></i-me>
		</view>
		<ifooter :page="page" @changePage="changePage"></ifooter>
	</view>
</template>

<script>
import ifooter from "../utils/footer.vue"
import iDashboard from "./dashboard.vue"
import iRegister from "../account/register/register.vue"
import iReport from "../report/report.vue"
import iMe from "../account/me/me.vue"
import iYearsRegister from "../account/register/yearsRegister.vue"
import iTrain from "../account/train/index.vue"
export default {
	components:{
		ifooter,
		iDashboard,
		iRegister,
		iReport,
		iMe,
		iYearsRegister,
		iTrain
	},
	data(){
		return{
			page: 0
		}
	},
	methods:{
		changePage(e){
			console.log(e)
			this.page = e
		},
		gotosaishi(){
			this.page = 2
		}
	}
}
</script>

<style>
</style>
