// ログインフォームの送信イベント
// フォームが送信（ボタンクリックやEnterキー）されたときの以下の関数を実行する。
document.getElementById('loginForm').addEventListener('submit',async function(event){
	// フォームの標準的な送信動作を止め、javascriptでのAPI送信を行い、ページのリロードなしで結果を処理できる
	event.preventDefault();
	
	//ユーザーの入力したユーザー名/メールアドレスとパスワードを取得する。
	// `const` は、一度値を設定したら変更しない変数（定数）を宣言するときに使う。
	const usernameOrEmail = document.getElementById('usernameOrEmail').value;
	const password = document.getElementById('password').value;
	
	// エラーメッセージを表示する領域を取得し、表示されている場合は非表示に戻す。
	    const errorMessageDiv = document.getElementById('errorMessage');
	    errorMessageDiv.style.display = 'none';
		
	
})