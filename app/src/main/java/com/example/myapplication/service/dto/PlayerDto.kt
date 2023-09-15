package com.example.myapplication.service.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
//entity that gets the api data without filtering it to db yet.
@Entity
data class PlayerDto(
    //////////////////////
    @SerializedName("dateBorn")
    val birthday: String,
    ///////////////////
    val dateSigned: String,
    val idAPIfootball: String,
    val idPlayer: String,
    val idPlayerManager: Any,
    val idSoccerXML: String,
    val idTeam: String,
    val idTeam2: String,
    val idTeamNational: Any,
    val idWikidata: Any,
    val intLoved: String,
    val intSoccerXMLTeamID: String,
    val strAgent: String,
    val strBanner: Any,
    ///////////////////
    @SerializedName("strBirthLocation")
    val birthLocation: String,
    ///////////////////
    val strCollege: Any,
    val strCreativeCommons: String,
    val strCutout: String,
    val strDescriptionCN: Any,
    val strDescriptionDE: Any,
    ///////////////////
    @SerializedName("strDescriptionEN")
    val englishDescription: String,
    ///////////////////
    val strDescriptionES: String,
    val strDescriptionFR: Any,
    val strDescriptionHU: Any,
    val strDescriptionIl: Any,
    val strDescriptionIT: Any,
    val strDescriptionJP: Any,
    val strDescriptionNL: Any,
    val strDescriptionNO: Any,
    val strDescriptionPL: Any,
    val strDescriptionPT: Any,
    val strDescriptionRU: Any,
    val strDescriptionSE: Any,
    val strEthnicity: String,
    val strFacebook: String,
    val strFanart1: String,
    val strFanart2: String,
    val strFanart3: Any,
    val strFanart4: Any,
    val strGender: String,
    val strHeight: String,
    ///////////////////
    @SerializedName("strInstagram")
    val instagram: String,
    ///////////////////
    val strKit: String,
    val strLocked: String,
    ///////////////////
    @SerializedName("strNationality")
    val nation: String,
    ///////////////////
    @SerializedName("strNumber")
    val number: String,
    ///////////////////
    val strOutfitter: String,
    ///////////////////
    @SerializedName("strPlayer")
    val fullName: String,
    ///////////////////
    val strPlayerAlternate: String,
    ///////////////////
    @SerializedName("strPosition")
    val position: String,
    ///////////////////
    val strRender: String,
    val strSide: String,
    val strSigning: String,
    ///////////////////
    @SerializedName("strSport")
    val sportType: String,
    ///////////////////
    val strStatus: String,
    ///////////////////
    @SerializedName("strTeam")
    val team: String,
    ///////////////////
    val strTeam2: String,
    ///////////////////
    @SerializedName("strThumb")
    val picture: String,
    ///////////////////
    val strTwitter: String,
    val strWage: String,
    val strWebsite: String,
    val strWeight: String,
    val strYoutube: String
)
