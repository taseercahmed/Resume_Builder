package com.semixtech.cv_resume_builder.helper

import com.semixtech.cv_resume_builder.db.Dao.UserskillDao
import com.semixtech.cv_resume_builder.db.Entity.*

data class TemplateDefaultModel(
    var fontfamily: String ="Arial",
    var color:String="#000000",
    var user:UserEntity= UserEntity("","","","","","","",""),
    var workhistory:UserHistoryEntity= UserHistoryEntity(0,"","","","","","",""),
    var userEducationEntity: UserEducationEntity= UserEducationEntity(0,"","","","",""),
    var userSkillsEntity: UserSkillsEntity= UserSkillsEntity(0,""),
    var userSummaryEntity: UserSummaryEntity=UserSummaryEntity(0,"")

) {
}