rootProject.name = "MainMenuComponentTSDStore"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()

    }
}

include(":composeApp")
include(":features:chat")
include(":features:crm")
include(":features:home")
include(":features:profile")
include(":features:tape")
include(":core:local-storage")
include(":core:network")
include(":core:app")
include(":common:video-player")
include(":common:file-provider")
include(":core:contact-provider")
/*--*/include(":features:application-sections:crm-section:notes-features")


include(":common")
include(":common:printer-impl")
include(":common:ip-camera-udp-client")

include(":core:recources")
include(":common:phone")
include(":common:printer-barcode-vkp")
include(":common:printer-barcode-tsc")
include(":common:camera-provider")
include(":common:scanner-usb-zebra")


/**application-sections**/
include(":features:application-sections")
/**chat-section**/
/*--*/include(":features:application-sections:chat-section")
/*--*/include(":features:application-sections:chat-section:chats-api")
/*--*/include(":features:application-sections:chat-section:chats-impl")
/**crm-section**/
/*--*/include(":features:application-sections:crm-section")

/**features**/
/*----*/include(":features:application-sections:crm-section:munu-crm-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:munu-crm-feature:menu-crm-api")
/*------*/include(":features:application-sections:crm-section:munu-crm-feature:menu-crm-impl")

/**features**/
/*----*/include(":features:application-sections:profile-section:notes-feature")
/** api and impl**/
/*------*/include(":features:application-sections:profile-section:notes-feature:notes-screens:notes-screens-api")
/*------*/include(":features:application-sections:profile-section:notes-feature:notes-screens:notes-screens-impl")

/**features**/
/*----*/include(":features:application-sections:profile-section:contragents-feature")
/** api and impl**/
/*------*/include(":features:application-sections:profile-section:contragents-feature:contragents-api")
/*------*/include(":features:application-sections:profile-section:contragents-feature:contragents-impl")

/**features**/
/*----*/include(":features:application-sections:profile-section:units-measurement-feature")
/** api and impl**/
/*------*/include(":features:application-sections:profile-section:units-measurement-feature:units-measurement-api")
/*------*/include(":features:application-sections:profile-section:units-measurement-feature:units-measurement-impl")

/**features**/
/*----*/include(":features:application-sections:crm-section:crm-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:crm-feature:crm-api")
/*------*/include(":features:application-sections:crm-section:crm-feature:crm-impl")

/**features**/
/*----*/include(":features:application-sections:crm-section:munu-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:munu-feature:munu-api")
/*------*/include(":features:application-sections:crm-section:munu-feature:menu-impl")

/**features**/
/*----*/include(":features:application-sections:profile-section:project-control-feature")
/** api and impl **/
/*------*/include(":features:application-sections:profile-section:project-control-feature:project-control-impl")
/*------*/include(":features:application-sections:profile-section:project-control-feature:project-control-api")
/**features**/
/*----*/include(":features:application-sections:profile-section:specifications-feature")
/** api and impl**/
/*------*/include(":features:application-sections:profile-section:specifications-feature:specifications-api")
/*------*/include(":features:application-sections:profile-section:specifications-feature:specifications-impl")

/**features**/
/*----*/include(":features:application-sections:profile-section:locations-feature")
/** api and impl**/
/*------*/include(":features:application-sections:profile-section:locations-feature:locations-api")
/*------*/include(":features:application-sections:profile-section:locations-feature:locations-impl")

/**home-section**/
/*--*/include(":features:application-sections:organization-section")
/** api and impl **/
/*----*/include(":features:application-sections:organization-section:organization-impl")
/*----*/include(":features:application-sections:organization-section:organization-api")

/**profile-section**/
/*--*/include(":features:application-sections:profile-section")
/**profile-feature**/
/*--*/include(":features:application-sections:profile-section:profile-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:profile-feature:profile-impl")
/*----*/include(":features:application-sections:profile-section:profile-feature:profile-api")


/**profile-ip-camera**/
/*--*/include(":features:application-sections:profile-section:ip-camera-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:ip-camera-feature:ip-camera-impl")
/*----*/include(":features:application-sections:profile-section:ip-camera-feature:ip-camera-api")

/*----*/include(":features:application-sections:profile-section:warehouse-feature")
/*----*/include(":features:application-sections:profile-section:tools-feature:ip-camera-api")
/*----*/include(":features:application-sections:profile-section:tools-feature:ip-camera-impl")


/*----*/include(":features:application-sections:profile-section:warehouse-feature:warehouse-api")
/*----*/include(":features:application-sections:profile-section:warehouse-feature:warehouse-impl")
/** printer-feature **/
/*--*/include(":features:application-sections:profile-section:printer-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:printer-feature:printer-impl")
/*----*/include(":features:application-sections:profile-section:printer-feature:printer-api")

/** goods-and-services-feature **/
/*--*/include(":features:application-sections:profile-section:goods-and-services-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:goods-and-services-feature:goods-and-services-impl")
/*----*/include(":features:application-sections:profile-section:goods-and-services-feature:goods-and-services-api")

/**profile-feature**/
/*--*/include(":features:application-sections:profile-section:products-menu-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:products-menu-feature:products-menu-impl")
/*----*/include(":features:application-sections:profile-section:products-menu-feature:products-menu-api")

/**profile-feature**/
/*--*/include(":features:application-sections:profile-section:categories-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:categories-feature:categories-impl")
/*----*/include(":features:application-sections:profile-section:categories-feature:categories-api")

/*--*/include(":features:application-sections:profile-section:product-categories-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:product-categories-feature:product-categories-impl")
/*----*/include(":features:application-sections:profile-section:product-categories-feature:product-categories-api")

/*--*/include(":features:application-sections:profile-section:catalog-products-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:catalog-products-feature:catalog-products-impl")
/*----*/include(":features:application-sections:profile-section:catalog-products-feature:catalog-products-api")

/**profile-section**/
/*--*/include(":features:application-sections:tape-section")
/** api and impl **/
/*----*/include(":features:application-sections:tape-section:tape-api")
/*----*/include(":features:application-sections:tape-section:tape-impl")

/**outhorization-feature**/
/*--*/include(":features:authorization-feature")
/** api and impl **/
/*----*/include(":features:authorization-feature:authorization-screen-api")
/*----*/include(":features:authorization-feature:authorization-screen-impl")

/*----*/include(":features:application-sections:tape-section:tape-api")