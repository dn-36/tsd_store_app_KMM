package com.datasource

import com.domain.repository.LocationsClientApi
import com.model.CompanyModel
import com.model.ContragentModel
import com.model.EntityModel
import com.model.LocationResponseModel
import com.model.WorkerModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.locations_network.LocationsClient

class LocationClientImpl (

    val sharedPrefsApi: SharedPrefsApi,

    val locationsClient: LocationsClient

): LocationsClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getLocations(): List<LocationResponseModel> {

        return locationsClient.getLocations().map {

            LocationResponseModel(

                id = it.id,
                adres = it.adres,
                name = it.name,
                contragent = if (it.contragent != null ) ContragentModel(

                    id = it.contragent!!.id,
                    name = it.contragent!!.name,
                    ui = it.contragent!!.ui,
                    own = it.contragent!!.own

                )  else null,

                default = it.default,
                ui = it.ui,
                email = it.email,
                entity = if ( it.entity != null ) EntityModel(

                    id = it.entity!!.id,
                    name = it.entity!!.name,
                    ui = it.entity!!.ui,
                    company = if ( it.entity!!.company != null ) CompanyModel(

                        id = it.entity!!.company!!.id,
                        name = it.entity!!.company!!.name,
                        creater_id = it.entity!!.company!!.creater_id,
                        company_id = it.entity!!.company!!.company_id,
                        created_at = it.entity!!.company!!.created_at,
                        updated_at = it.entity!!.company!!.updated_at,
                        own = it.entity!!.company!!.own

                    ) else null,
                    own = it.entity!!.own,
                    email = it.entity!!.email,
                    fact_address = it.entity!!.fact_address,
                    inn = it.entity!!.inn,
                    kpp = it.entity!!.kpp,
                    nds = it.entity!!.nds,
                    ogrn = it.entity!!.ogrn,
                    okpo = it.entity!!.okpo,
                    phone = it.entity!!.phone,
                    ur_address = it.entity!!.ur_address

                ) else null,

                phone = it.phone,
                text = it.text,
                workers = it.workers?.map {

                    WorkerModel(

                        id = it.id,
                        name = it.name,
                        telegram = it.telegram,
                        wechat = it.wechat,
                        ui = it.ui,
                        email = it.email,
                        phone = it.phone,
                        whatsapp = it.whatsapp,
                        text = it.text

                    )

                }
            )

        }

    }

    override suspend fun deleteLocation(id: Int) {

        locationsClient.deleteLocation(id)

    }

}