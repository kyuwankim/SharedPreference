#데이터 저장 방법
--
Android는 지속적인 애플리케이션 데이터를 저장하는 여러 옵션을 제공합니다. 선택하는 솔루션은 데이터를 해당 애플리케이션 전용 데이터로 할지 다른 애플리케이션(및 사용자)이 액세스할 수 있도록 할지, 그리고 데이터가 필요로 하는 공간 등 특정 필요에 따라 다릅니다.

1. 공유 기본 설정 : 전용 원시 데이터를 키-값 쌍으로 저장합니다.
2. 내부 저장소 : 전용 데이터를 기기 메모리에 저장합니다.
3. 외부 저장소 : 공용 데이터를 공유 외부 저장소에 저장합니다.
4. SQLite 데이터베이스 : 구조적 데이터를 전용 데이터베이스에 저장합니다.
5. 네트워크 연결 : 자신의 네트워크 서버를 사용하여 데이터를 웹에 저장합니다.



# SharedPreference
--

SharedPreferences 클래스는 원시 데이터 유형의 지속적인 키-값 쌍을 저장 및 검색할 수 있는 일반 프레임워크를 제공합니다. SharedPreferences를 사용하여 부울, 부동 수, 정수, long 및 문자열 등 원시 데이터를 저장할 수 있습니다. 이 데이터는 (애플리케이션이 중지된 경우에도) 사용자 세션 동안 지속됩니다.

애플리케이션에 대한 SharedPreferences 객체를 가져오려면 다음 두 메서드 중 하나를 사용합니다.

getSharedPreferences() - 첫 번째 매개변수로 지정하는, 이름으로 식별하는 여러 기본 설정 파일이 필요한 경우 이 메서드를 사용합니다.
getPreferences() - 액티비티에 대한 단일 기본 설정 파일이 필요한 경우 이 메서드를 사용합니다. 이는 액티비티에 대한 기본 설정 파일일 뿐이므로 이름을 제공하지 않습니다.
값을 쓰려면:

edit()을 호출하여 SharedPreferences.Editor를 가져옵니다.
putBoolean() 및 putString()과 같은 메서드를 사용하여 값을 추가합니다.
commit()을 사용하여 새 값을 커밋합니다.
값을 읽으려면 getBoolean() 및 getString()와 같은 SharedPreferences 메서드를 사용합니다.

<pre>
<code>
public void saveSetting(View view){
        // 1. Preference 생성하기
        SharedPreferences sharedPref = getSharedPreferences(SAHRED_FILE,Context.MODE_PRIVATE);
        // 2. SharedPreference에 값을 입력하기 위해서는 에디터를 통해서만 가능
        SharedPreferences.Editor editor = sharedPref.edit();

        //editor.putInt( "키", "값");
        editor.putString("email",    editName.getText().toString());
        editor.putBoolean("shuffle", switchShuffle.isChecked());

        // 3. 입력된 값을 반영
        editor.commit();
    }

    public void loadSetting(){
        SharedPreferences sharedPref = getSharedPreferences(SAHRED_FILE,Context.MODE_PRIVATE);

        // 프로퍼티 가져오기
        String email = sharedPref.getString("email", "");
        boolean shuffle = sharedPref.getBoolean("shuffle", false);

        // 화면에 세팅
        editName.setText(email);
        switchShuffle.setChecked(shuffle);
    }
</code>
</pre>