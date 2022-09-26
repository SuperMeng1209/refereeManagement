<template>
	<view class="nav">
		<uni-nav-bar 
			dark 
			:fixed="true" 
			shadow 
			background-color="#036a52" 
			status-bar 
			left-icon="left" 
			left-text="首页"
			title="裁判注册" 
			@clickLeft="leftClick" 
		/>
		<view v-if="!role">
			<view class="u-content">
						<view class="u-content__circle">
							<u-icon
								name="info"
								color="#fff"
								size="30"
							></u-icon>
						</view>
						<text class="u-content__normal">暂无权限，请联系管理员为裁判员注册</text>
					</view>
		</view>
		<view v-else>
			<uni-card title="个人基本信息(用于记录个人信息)">
				<u--form :labelWidth="80" labelPosition="left" :model="model1" ref="form1" :rules="rules">
					<u-form-item label="姓名" prop="userInfo.realname" borderBottom>
						<u--input v-model="model1.userInfo.realname" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="年龄" prop="userInfo.age" borderBottom>
						<u--input v-model="model1.userInfo.age" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="性别" prop="userInfo.sex" borderBottom @click="showSex = true">
						<u--input v-model="model1.userInfo.sex" disabled disabledColor="#ffffff" placeholder="请选择性别" borderBottom></u--input>
						<u-icon slot="right" name="arrow-right"></u-icon>
					</u-form-item>
					
					<u-form-item label="手机号" prop="userInfo.phone" borderBottom>
						<u--input v-model="model1.userInfo.phone" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="身份证号码" prop="userInfo.idcard" borderBottom>
						<u--input v-model="model1.userInfo.idcard" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="证书编号" prop="userInfo.certificateNum" borderBottom>
						<u--input v-model="model1.userInfo.certificateNum" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="证书级别" prop="userInfo.certificateLeave" borderBottom>
						<u--input v-model="model1.userInfo.certificateLeave" border="none"></u--input>
					</u-form-item>
					
					<u-form-item label="身高" prop="userInfo.cm" borderBottom>
						<!-- 注意：由于兼容性差异，如果需要使用前后插槽，nvue下需使用u--input，非nvue下需使用u-input -->
						<!-- #ifndef APP-NVUE -->
						<u-input placeholder="请输入整数身高" v-model="model1.userInfo.cm">
						<!-- #endif -->
						<!-- #ifdef APP-NVUE -->
						<u--input placeholder="请输入整数身高" v-model="model1.userInfo.cm">
						<!-- #endif -->
							<template slot="suffix">
								<text>厘米(cm)</text>
							</template>
						<!-- #ifndef APP-NVUE -->
						</u-input>
						<!-- #endif -->
						<!-- #ifdef APP-NVUE -->
						</u--input>
						<!-- #endif -->
					</u-form-item>
					
					<u-form-item label="体重" prop="userInfo.kg" borderBottom>
						<!-- 注意：由于兼容性差异，如果需要使用前后插槽，nvue下需使用u--input，非nvue下需使用u-input -->
						<!-- #ifndef APP-NVUE -->
						<u-input placeholder="请输入整数体重" v-model="model1.userInfo.kg">
						<!-- #endif -->
						<!-- #ifdef APP-NVUE -->
						<u--input placeholder="请输入整数体重" v-model="model1.userInfo.kg">
						<!-- #endif -->
							<template slot="suffix">
								<text>公斤(kg)</text>
							</template>
						<!-- #ifndef APP-NVUE -->
						</u-input>
						<!-- #endif -->
						<!-- #ifdef APP-NVUE -->
						</u--input>
						<!-- #endif -->
					</u-form-item>
					
					<u-form-item label="家庭地址" prop="userInfo.place" borderBottom>
						<u--input v-model="model1.userInfo.place" border="none"></u--input>
					</u-form-item>
				</u--form>
			</uni-card>
			
			<uni-card title="帐户信息(用于用户登录系统)" style="margin-top: 10px">
				<u--form :labelWidth="80" labelPosition="left" :model="model1" ref="form2" :rules="rules">
					<u-form-item label="用户名" prop="userInfo.username" borderBottom>
						<u--input v-model="model1.userInfo.username" border="none"></u--input>
					</u-form-item>
					<u-form-item label="密码" prop="userInfo.password" borderBottom>
						<u--input type="password" v-model="model1.userInfo.password" border="none"></u--input>
					</u-form-item>
					<u-form-item label="创建人" prop="userInfo.createName" borderBottom>
						<u--input v-model="model1.userInfo.createName" border="none"></u--input>
					</u-form-item>
					<u-form-item label="权限" prop="userInfo.role" borderBottom>
						<u-checkbox-group v-model="model1.userInfo.role" placement="column">
							<u-checkbox label="管理员" name="admin"></u-checkbox>
							<u-checkbox label="裁判员" name="裁判员"></u-checkbox>
							<u-checkbox label="五人制/沙足裁判员" name="五人制/沙足裁判员"></u-checkbox>
							<u-checkbox label="裁判监督" name="裁判监督"></u-checkbox>
							<u-checkbox label="五人制裁判监督" name="五人制裁判监督"></u-checkbox>
							<u-checkbox label="裁判技术讲师" name="裁判技术讲师"></u-checkbox>
							<u-checkbox label="裁判体能讲师" name="裁判体能讲师"></u-checkbox>
						</u-checkbox-group>
					</u-form-item>
				</u--form>
			</uni-card>
			
			
			<view style="margin:15px">
				<u-button type="success" @click="submit">提交</u-button>
			</view>
			<u-action-sheet :show="showSex" :actions="actions" title="请选择性别" @close="showSex = false" @select="sexSelect" />
		</view>
	</view>
</template>

<script>
	import yearRegister from "./yearsRegister.vue"
	export default {
		components: {
			yearRegister
		},
		data() {
			return {
				rules: {
					"userInfo.username":{
						type: 'string',
						required: true,
						message: '请填写用户名',
						trigger: ['blur', 'change']
					},
					"userInfo.passowrd": {
						type: 'string',
						required: true,
						message: '请填写密码',
						trigger: ['blur', 'change']
					},
					"userInfo.createName": {
						type: 'string',
						required: true,
						message: '请填写创建人',
						trigger: ['blur', 'change']
					},
					"userInfo.realname": {
						type: 'string',
						required: true,
						message: '请填写姓名',
						trigger: ['blur', 'change']
					},
					"userInfo.sex": {
						type: 'string',
						max: 1,
						required: true,
						message: '请选择男或女',
						trigger: ['blur', 'change']
					},
					"userInfo.age": {
						type: 'string',
						max: 2,
						required: true,
						message: '请输入年龄',
						trigger: ['blur', 'change']
					},
					"userInfo.phone": {
						type: 'string',
						max: 11,
						required: true,
						message: '请输入手机号',
						trigger: ['blur', 'change']
					},
					"userInfo.idcard": {
						type: 'string',
						required: true,
						message: '请填写身份证号码',
						trigger: ['blur', 'change']
					},
					"userInfo.cm": {
						type: 'string',
						required: true,
						message: '请填写身高',
						trigger: ['blur', 'change']
					},
					"userInfo.kg": {
						type: 'string',
						required: true,
						message: '请填写体重',
						trigger: ['blur', 'change']
					},
					"userInfo.place": {
						type: 'string',
						required: true,
						message: '请填写家庭地址',
						trigger: ['blur', 'change']
					}
				},
				role: false,
				showRole: false,
				showSex: false,
				actions: [
					{ name: '男' },
					{ name: '女' }
				],
				model1: {
					userInfo: {
						realname: '',
						sex: '',
						idcard: '',
						cm: '',
						kg: '',
						place: '',
						username: '',
						password: '',
						phone: '',
						age: '',
						role: [],
						certificateNum: '',
						certificateLeave: ''
					}
				}
			}
		},
		methods: {
			submit(){
				this.$refs.form1.validate().then(res =>{
					this.$refs.form2.validate().then(re =>{
						for(var i=0;i<=this.model1.userInfo.role.length - 1;i++){
							if(this.model1.userInfo.role[i] == 'admin'){
								this.model1.userInfo.role = 'admin'
								break;
							}
							if(i == this.model1.userInfo.role.length - 1){
								if(this.model1.userInfo.role[i] == 'admin'){
									this.model1.userInfo.role = 'admin'
									break;
								}else{
									this.model1.userInfo.role = 'caipan'
								}
							}
						}
						uni.request({
							url: 'http://'+this.$serviceUrl+':8098/userApi/addUser',
							method: "POST",
							data: this.model1.userInfo,
							success: (res) =>{
									uni.request({
										url: 'http://'+this.$serviceUrl+':8098/msgApi/addMsg',
										method: "POST",
										data: { userid: res.data ,msg: '管理员为您创建帐号完成！' ,status: '未读'},
										success: (re) =>{
											uni.$u.toast('添加成功')
										}
									})
							}
						})
					})
				}).catch(error =>{
					uni.$u.toast('校验失败')
				})
			},
			sexSelect(e){
				this.model1.userInfo.sex = e.name
				this.$refs.form1.validateField('userInfo.sex')
			},
			leftClick(){
				uni.redirectTo({
					url: '/pages/dashboard/index'
				});
			}
		},
		mounted() {
			uni.getStorage({
				key: 'user',
				success:(res) =>{
					if(res.data.role == 'admin'){
						this.role = true
					}else{
						this.role = false
					}
				}
			})
		}
	}
</script>

<style lang="scss" scoped>
.nav{
	margin-top: -20px;
}
.u-content {
		padding: 150px 60px 0;
		@include flex(column);
		align-items: center;
		justify-content: center;

		&__circle {
			background-color: $u-error;
			@include flex;
			border-radius: 100px;
			width: 60px;
			height: 60px;
			align-items: center;
			justify-content: center;
		}

		&__normal {
			font-size: 15px;
			color: $u-error;
			margin-top: 15px;
		}
	}
</style>
