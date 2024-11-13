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
include(":core:contact-provider")
/*--*/include(":features:application-sections:crm-section:notes-features")


include(":common")
include(":core:recources")
include(":common:phone")
include(":common:printer-barcode-vkp")
include(":common:printer-barcode-tsc")
include(":common:camera-provider")



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
/*----*/include(":features:application-sections:crm-section:notes-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:notes-feature:notes-screens:notes-screens-api")
/*------*/include(":features:application-sections:crm-section:notes-feature:notes-screens:notes-screens-impl")

/**features**/
/*----*/include(":features:application-sections:crm-section:contragents-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:contragents-feature:contragents-api")
/*------*/include(":features:application-sections:crm-section:contragents-feature:contragents-impl")

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
/*----*/include(":features:application-sections:crm-section:project-conterol-feature")
/** api and impl **/
/*------*/include(":features:application-sections:crm-section:project-conterol-feature:project_control-impl")
/*------*/include(":features:application-sections:crm-section:project-conterol-feature:project-control-api")
/**features**/
/*----*/include(":features:application-sections:crm-section:specifications-feature")
/** api and impl**/
/*------*/include(":features:application-sections:crm-section:specifications-feature:specifications-api")
/*------*/include(":features:application-sections:crm-section:specifications-feature:specifications-impl")

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
/*----*/include(":features:application-sections:profile-section:warehouse-feature")

/*----*/include(":features:application-sections:profile-section:warehouse-feature:warehouse-api")
/*----*/include(":features:application-sections:profile-section:warehouse-feature:warehouse-impl")
/** printer-feature **/
/*--*/include(":features:application-sections:profile-section:printer-feature")
/** api and impl **/
/*----*/include(":features:application-sections:profile-section:printer-feature:printer-impl")
/*----*/include(":features:application-sections:profile-section:printer-feature:printer-api")

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