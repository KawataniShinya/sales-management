# sales-management
[自己研鑽用]Webアプリケーションサンプル

## Description
For my self improvement.<br>
Webアプリケーション実装の学習のため、販売管理システムを題材に作成した架空のアプリケーション。<br>
業務利用を想定して稼働するWebアプリケーションとして、フロントエンド/サーバーサイド/永続化領域(データベース)を通して1つのプロジェクトとして構築。<br>
また、APIとしても利用できるようサービスを公開。<br>
マイクロサービス化を見越し、部分的にドメイン駆動設計の考えに沿い、ビジネス的関心を独立して実装。<br>
<br>
現時点で実装されている機能は社員管理機能のみで、未完成ながらも下記ドメインが含まれている。
- 認証
- 顧客
- 部署
- 汎用コード
- ログ管理
- 社員

<br>
サーバー上のTomcatやHerokuにデプロイ可能。<br>
<br>

## Components
プロジェクト、アプリケーションを構成する要素は下記の通り。(生成元要素やトランスパイル元も含む)
- Java
- Spring Boot
- Thymeleaf
- MySQL
- PostgreSQL
- SCSS
- CSS
- JavaScript
- TypeScript
- Bootstrap
- Google Fonts
- PlantUML
- OpenAPI

## Development Environment And Tools
開発環境や使用されている支援ツールは下記の通り。
- Intellij IDEA
- Gradle
- JUnit
- DBUnit
- Visual Studio Code
- Live Server
- Live Sass Compiler

## Demo
https://fathomless-stream-18403.herokuapp.com/apl/

### 例. ログイン
![01](https://user-images.githubusercontent.com/102776020/176465788-4e476cea-0c76-4daa-81ea-d68855a77d16.gif)

### 例. 社員情報検索
![02](https://user-images.githubusercontent.com/102776020/176465896-a2426a55-8553-450d-a649-e663297c45f6.gif)

### 例. 社員情報変更登録
![03](https://user-images.githubusercontent.com/102776020/176465914-7a34d9fb-99a7-47fb-84ff-dcc2c894fede.gif)

### 例. 社員情報詳細更新
![04](https://user-images.githubusercontent.com/102776020/176465951-19a87a90-7c74-4d47-8c05-07e87414dcb9.gif)

### 例. 社員情報詳細削除
![05](https://user-images.githubusercontent.com/102776020/176465971-ad175464-b4f6-4d3c-9c1f-a6eb6f06573f.gif)

### 例. 社員情報検索API
リクエスト
```
curl -v -G http://localhost:8080/apl/api/staffs --data-urlencode "departmentCd=D20001" --data-urlencode "userName=テスト"
```
レスポンス
```
{"count":2,"limitSize":25,"page":1,"staffs":[{"userId":"STF0000003","familyName":...},{...}],"errors":{"field":{},"global":[]}}
```

## Usage
システムログイン画面から認証情報(T_MST_AUTHテーブル)に登録されたユーザーID、パスワードを入力。
左メニューからメニューを選択し、各機能単位でデータ操作を行う。
(操作例はDemo項目ご参照)

## Install
アプリケーションを稼働させるための環境を構築する。
ローカルまたはHerokuへの環境構築方法を示す。

### 1. ローカルでのアプリケーション環境構築
#### 1-1. MySQLの準備
アプリケーションから使用されるデータを格納するデータベースを準備。<br>
公式HP等からMySQLをインストール。<br>
https://www.mysql.com/jp/<br>
<br>
アプリケーションで使用するデータベースを作成。(例としてデータベース名をpjdbとする。)<br>
```
create database pjdb;
```
<br>
データベースへ接続するユーザーを作成。
(例としてユーザー名をpjusr1、パスワードをpjusr1zaq12wsxとする。)<br>

```
create user pjusr1@localhost identified with mysql_native_password by 'pjusr1zaq12wsx';
```
<br>
ユーザーに操作権限を付与。<br>

```
grant all on pjdb.* to pjusr1@localhost;
```
<br>
データベースにテーブルを作成。resources配下のddl(MySQL用)を使用。<br>

```
mysql -u pjusr1 -h localhost pjdb --password="pjusr1zaq12wsx" < T_MST_AUTH_dump_and_ddl.sql
mysql -u pjusr1 -h localhost pjdb --password="pjusr1zaq12wsx" < T_MST_CUSTOMER_dump_and_ddl.sql
mysql -u pjusr1 -h localhost pjdb --password="pjusr1zaq12wsx" < T_MST_DEPARTMENT_dump_and_ddl.sql
mysql -u pjusr1 -h localhost pjdb --password="pjusr1zaq12wsx" < T_MST_GENERIC_CD_dump_and_ddl.sql
mysql -u pjusr1 -h localhost pjdb --password="pjusr1zaq12wsx" < T_MST_STAFF_dump_and_ddl.sql
```
<br>
各テーブルに任意のデータを格納。<br>
特に認証情報(T_MST_AUTHテーブル)のパスワード(PASSWORD)はbcrypt形式で登録。<br>
<br>

#### 1-2. PostgreSQLの準備
アプリケーションから出力されるログデータを格納するデータベースを準備。<br>
公式HP等からPostgreSQLをインストール。<br>
https://www.enterprisedb.com/downloads/postgres-postgresql-downloads<br>
<br>
ユーザー(ロール)を作成する。(例としてユーザー名をpjusr1、パスワードをpjusr1zaq12wsxとする。)<br>

```
create role pjusr1 with login password 'pjusr1zaq12wsx'; 
```
<br>
アプリケーションで使用するデータベース、を作成。<br>
resources配下のddl(PostgreSQL用)を使用。<br>
(データベース名をpjdb、スキーマ名をpjschema1としている。)<br>

```
psql -h localhost -p 5432 -U pjusr1 -d pjdb -f ./postgresql_dump_pjdb-schema.sql;
```
<br>

#### 1-3. 環境変数の設定
各データベースへの接続は、環境変数に設定されたデータソースを参照している。<br>
システム環境変数に下記情報を設定。(ユーザー名やパスワード等の設定内容は上記例の通り。)
```
SPRING_DATASOURCE_SYSTEM01_URL : jdbc:postgresql://localhost:5432/pjdb?currentSchema=pjschema1
SPRING_DATASOURCE_SYSTEM01_USERNAME : pjusr1
SPRING_DATASOURCE_SYSTEM01_PASSWORD : pjusr1zaq12wsx
SPRING_DATASOURCE_APPLICATION01_URL : jdbc:mysql://localhost:3306/pjdb
SPRING_DATASOURCE_APPLICATION01_USERNAME : pjusr1
SPRING_DATASOURCE_APPLICATION01_PASSWORD : pjusr1zaq12wsx
```

#### 1-4. アプリケーションの起動
統合開発環境やGradleコマンドラインから、タスクbootRunを実行。<br>
```
gradle bootRun
```
<br>

#### 1-5. アプリケーションへの接続
ブラウザからポート8080、コンテキストパスaplにアクセス。<br>
```
http://localhost:8080/apl/login
```
<br>

### 2. Herokuでのアプリケーション環境構築
#### 2-1. Herokuの準備(公式手順のため詳細割愛)
https://jp.heroku.com/<br>
Herokuにアカウントを登録する。<br>
HerokuCLIをローカルにインストールする。<br>
Heroku上にアプリを作成する。<br>
MySQLやPostgreSQLのアドオンを利用するため、クレジットカード登録をする。(ただし無料の範囲で使用する。)<br>
(個人設定) -> Account setting -> Billing -> Billing Information<br>
<br>

#### 2-2. MySQLの準備
HerokuアプリにアドオンとしてMySQLを追加する。<br>
(アプリ) -> Resources -> Add-ons<br>
![heroku_addon](https://user-images.githubusercontent.com/102776020/176582328-d029f8f6-aacd-4288-9c7a-c03e788c444c.jpg)

<br>
データベースにテーブルを作成。resources配下のddl(MySQL用)を使用。<br>
指定するユーザーやパスワード等は、Herokuに作成されたものを指定する。<br>

```
mysql -u (ユーザーID) -h (ホスト名) (データベース名) --password=(パスワード) < T_MST_AUTH_dump_and_ddl.sql
mysql -u (ユーザーID) -h (ホスト名) (データベース名) --password=(パスワード) < T_MST_CUSTOMER_dump_and_ddl.sql
mysql -u (ユーザーID) -h (ホスト名) (データベース名) --password=(パスワード) < T_MST_DEPARTMENT_dump_and_ddl.sql
mysql -u (ユーザーID) -h (ホスト名) (データベース名) --password=(パスワード) < T_MST_GENERIC_CD_dump_and_ddl.sql
mysql -u (ユーザーID) -h (ホスト名) (データベース名) --password=(パスワード) < T_MST_STAFF_dump_and_ddl.sql
```
<br>
各テーブルに任意のデータを格納。<br>
特に認証情報(T_MST_AUTHテーブル)のパスワード(PASSWORD)はbcrypt形式で登録。<br>
<br>

#### 2-3. PostgreSQLの準備
HerokuアプリにアドオンとしてPostgreSQLを追加する。<br>
(アプリ) -> Resources -> Add-ons<br>
<br>
データベースにテーブルを作成。resources配下のddl(PostgreSQL用)を使用。<br>
ddl内で指定するユーザーやパスワード等は、Herokuに作成されたものを指定する。<br>
```
psql -h (ホスト名) -p 5432 -U (ユーザー名) -d (データベース名) -f ./postgresql_dump_pjdb-schema.sql;
```
<br>

#### 2-4. 環境変数の設定
各データベースへの接続は、環境変数に設定されたデータソースを参照している。<br>
Herokuのシステム環境変数に下記情報を設定。(ユーザー名やパスワード等の設定内容はHerokuで指定されたものに従う。)<br>
(アプリ) -> Setting -> Config Vars<br>
![envVal](https://user-images.githubusercontent.com/102776020/176582433-291ba61d-a033-4ec1-a823-65d06eb231ee.png)

```
SPRING_DATASOURCE_SYSTEM01_URL : jdbc:postgresql://(PostgreSQLホスト名)):5432/(PostgreSQLデータベース名)?currentSchema=pjschema1
SPRING_DATASOURCE_SYSTEM01_USERNAME : (PostgreSQLユーザー名)
SPRING_DATASOURCE_SYSTEM01_PASSWORD : (PostgreSQLパスワード)
SPRING_DATASOURCE_APPLICATION01_URL : jdbc:mysql://(MySQLホスト名):3306/(MySQLデータベース名)
SPRING_DATASOURCE_APPLICATION01_USERNAME : (MySQLユーザー名)
SPRING_DATASOURCE_APPLICATION01_PASSWORD : (MySQLパスワード)
```
<br>
また、HerokuにGitデプロイするためのGradleタスク名も環境変数として定義する。<br>

```
GRADLE_TASK : herokuDeploy
```
<br>

#### 2-5. gradleビルドパックを追加
ビルド時に参照されるBuildpacksにgradleを追加。
```
heroku create --buildpack https://github.com/heroku/heroku-buildpack-gradle.git
heroku buildpacks:add --index 1 heroku/gradle -a (Herokuアプリ名)
```
Settings -> Buildpacks<br>
![buildpacks](https://user-images.githubusercontent.com/102776020/177272153-90ccafb2-4449-4469-bb53-d6824607855a.JPG)

#### 2-6. Herokuへのデプロイ
Git config にHerokuのURLを指定して[remote "heroku"]を登録。
```
[remote "heroku"]
	url = https://git.heroku.com/(Herokuアプリ名).git
	fetch = +refs/heads/*:refs/remotes/heroku/*
```
<br>
Herokuアプリケーションにデプロイ。

```
git push heroku main
```

#### 2-7. アプリケーションへの接続
ブラウザからHerokuURLにコンテキストパスaplでアクセス。<br>
```
https://(Herokuアプリ名).herokuapp.com/apl/
```
<br>

## Licence
Licensed under the MIT License.

## Author
[KawataniShinya](https://github.com/KawataniShinya)
