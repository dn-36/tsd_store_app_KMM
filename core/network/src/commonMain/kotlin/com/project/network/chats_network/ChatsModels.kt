package com.project.network.chats_network

import kotlinx.serialization.Serializable

@Serializable
data class ChatsResponse(
    val name: String? = null,
    val ui: String? = null,
    val count_new_message: Int? = null,
    val image: String? = null,
    val users: List<User>? = listOf(),
    val project: Project? = null,
    val lastMessageUi: String? = null,
    val user_ui: String? = null,
    val user_image: String? = null,
    val message: String? = null,
    val data: Long? = null,
    val created_at: String? = null
)

@Serializable
data class User(
    val phone: String? = null,
    val name: String? = null,
    val ui: String? = null,
    val active: Int? = null,
    val image: String? = null,
    val id: Int? = null,
    val laravel_through_key: Int? = null
)

@Serializable
data class Project(
    val id: Int? = null,
    val name: String? = null,
    val creater_id: Int? = null,
    val company_id: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val entity_id: Int? = null,
    val active: Int? = null,
    val entity_client_id: Int? = null
)
/*
 [Response(
 id=172, active=0, user_id=282, company_id=147, created_at=2024-10-01T17:04:05.000000Z,
 updated_at=2024-10-16T16:13:41.000000Z, verify=0, company=Company(id=147, name=Личный профиль,
 ui=8sdh6a9y-v81dbox0-gdz2ni5j, creater_id=282, created_at=2024-10-01T17:04:05.000000Z,
 updated_at=2024-10-10T15:06:08.000000Z, url=null, country=7, title=null, description=null,
  logo=null, favicon=null, phone=null, email=null, fon=#ebebeb, slider_title=null, slider_view=1,
  address=null, metric=null, fon_product=#ebebeb, tg=null, wh=null, wc=null, valuta_id=null, inn=null,
   type=none, user_pay=null, password_pay=null, point=null, point_image=null, tags=null,
   video=null, video_mobile=null, cart=null, head_text=null, head_image=null, head_title=null,
   head_button=null, head_href=null, lang_default_id=1, valuta=null)),
   Response(id=174, active=1, user_id=282, company_id=4,
   created_at=2024-10-01T17:22:29.000000Z, updated_at=2024-10-18T11:49:45.000000Z, verify=0, company=Company(id=4, name=c17, ui=xsg0i1qr-3zqysaco-szh74wov, creater_id=6, created_at=2023-12-29T09:26:57.000000Z, updated_at=2024-10-10T15:06:07.000000Z, url=c17, country=7, title=Производственная компания ООО Компонент17, description=ООО Компонент17, logo=company/3583f8e520a22bbf4e73e0e7d3e24397.jpeg, favicon=null, phone=+7 [496] 789-49-20, email=info@component17.com, fon=hsl(0,0%,92%), slider_title=null, slider_view=1, address=Москва нагорный проезд 7 стр 1, metric=null, fon_product=hsl(0,1%,91%), tg=https://t.me/mobilecomputercom, wh=whatsapp://send?phone=79266797551&text=Добрый день!, wc=null, valuta_id=50, inn=null, type=none, user_pay=null, password_pay=null, point=null, point_image=null, tags=null, video=null, video_mobile=null, cart=null, head_text=null, head_image=null, head_title=null, head_button=null, head_href=null, lang_default_id=1, valuta=Valuta(id=50, company_id=null, name=RUR, system_name=RUR, curs=1, ui=GE35GE25, active=1, created_at=null, updated_at=null))), Response(id=175, active=0, user_id=282, company_id=148, created_at=2024-10-11T14:36:21.000000Z, updated_at=2024-10-17T10:05:59.000000Z, verify=0, company=Company(id=148, name=тест организации, ui=ivc26zd5-r17tho0z-b0xrhisy, creater_id=282, created_at=2024-10-11T14:36:21.000000Z, updated_at=2024-10-11T14:36:21.000000Z, url=wwwgooglecom, country=7, title=null, description=null, logo=null, favicon=null, phone=null, email=null, fon=#ffffff, slider_title=null, slider_view=1, address=null, metric=null, fon_product=#ebebeb, tg=null, wh=null, wc=null, valuta_id=null, inn=83789347893474389, type=cafe, user_pay=null, password_pay=null, point=null, point_image=null, tags=#jhsbcfdhj, video=null, video_mobile=null, cart=null, head_text=null, head_image=null, head_title=null, head_button=null, head_href=null, lang_default_id=null, valuta=null)), Response(id=191, active=0, user_id=282, company_id=161, created_at=2024-10-16T15:50:20.000000Z, updated_at=2024-10-17T10:06:11.000000Z, verify=0, company=Company(id=161, name=Новая организация, ui=3dmil2bp-vajtnq2y-58v03oqr, creater_id=282, created_at=2024-10-16T15:50:20.000000Z, updated_at=2024-10-16T15:50:20.000000Z, url=, country=7, title=null, description=null, logo=null, favicon=null, phone=null, email=null, fon=#ffffff, slider_title=null, slider_view=1, address=null, metric=null, fon_product=#ebebeb, tg=null, wh=null, wc=null, valuta_id=null, inn=null, type=null, user_pay=null, password_pay=null, point=null, point_image=null, tags=null, video=null, video_mobile=null, cart=null, head_text=null, head_image=null, head_title=null, head_button=null, head_href=null, lang_default_id=null, valuta=null)), Response(id=193, active=0, user_id=282, company_id=163, created_at=2024-10-17T10:06:11.000000Z, updated_at=2024-10-17T11:43:22.000000Z, verify=0, company=Company(id=163, name=ggg, ui=57a9qliv-gxb0nomf-f1ieug85, creater_id=282, created_at=2024-10-17T10:06:11.000000Z, updated_at=2024-10-17T10:06:11.000000Z, url=t
 */

/*
hatsResponse(name=тест 2, ui=dw619qfj-9xcwp78q-wp72in5v, count_new_message=0, image=message/6f57ea36bdb60631533adaac4d81d390.jpeg, users=[User(phone=+79963799050, name=DIMA, ui=nmhdo3jc-ht9v2nxa-08iv2hsd, active=1, image=null, id=254, laravel_through_key=84), User(phone=+79231351946, name=Nika, ui=bdeto1g8-wr2dulzi-s26im8b1, active=1, image=null, id=282, laravel_through_key=84), User(phone=+79963799051, name=Dima, ui=mfygzrcx-7eywunxv-nacxflvq, active=1, image=null, id=284, laravel_through_key=84), User(phone=+79231351946, name=Nika, ui=bdeto1g8-wr2dulzi-s26im8b1, active=1, image=null, id=282, laravel_through_key=84), User(phone=+79231351946, name=Nika, ui=bdeto1g8-wr2dulzi-s26im8b1, active=1, image=null, id=282, laravel_through_key=84)], project=Project(id=13, name=разработка Android, creater_id=6, company_id=4, created_at=2024-01-16T18:29:16.000000Z, updated_at=2024-01-16T18:29:16.000000Z, entity_id=null, active=1, entity_client_id=null), lastMessageUi=va06m1qe-t69h5y2o-9382bsmi, user_ui=mfygzrcx-7eywunxv-nacxflvq, user_image=null, message=837893478934, data=1729530748, created_at=2024-10-21T17:12:28.000000Z)>>>>>>>>>
 */

