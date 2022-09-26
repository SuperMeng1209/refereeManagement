<template>
	<view style="z-index:999">
		<u-tabbar
			:value="page"
			:fixed="true"
			@change="click1"
			:placeholder="true"
			:safeAreaInsetBottom="true"
			activeColor="rgba(42,188,89,.85)"
		>
			<u-tabbar-item text="首页" icon="home" :name="0"></u-tabbar-item>
			<u-tabbar-item text="注册管理" icon="photo" v-if="role == 'admin'" :name="1"></u-tabbar-item>
			<u-tabbar-item text="年度注册" icon="edit-pen" v-if="role == 'caipanyuan'" :name="2"></u-tabbar-item>
			<u-tabbar-item text="培训管理" icon="thumb-up" :name="3"></u-tabbar-item>
			<u-tabbar-item text="赛事新闻" icon="play-right" :name="4"></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" :name="5"></u-tabbar-item>
		</u-tabbar>
	</view>
</template>

<script>
	export default {
		props: ['page'],
		data() {
			return {
				role: false
			}
		},
		methods: {
			click1(e) {
				this.$emit('changePage',e)
			}
		},
		mounted() {
			uni.getStorage({
				key: 'user',
				success:(res) =>{
					if(res.data.role == 'admin'){
						this.role = 'admin'
					}else{
						this.role = 'caipanyuan'
					}
				}
			})
		}
	}
</script>

<style>
.u-tabbar__content{
	z-index: 999;
}
</style>
