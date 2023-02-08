package com.example.guryihii.Epoxy

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.guryihii.model.domain.Property

class PropertyEpoxyController: TypedEpoxyController<List<Property>>() {
    override fun buildModels(data: List<Property>?) {
        if (data == null || data.isEmpty() ){
            //todo
            return
        }

        data.forEach{property ->
            PropertyEpoxyModel(property).id(property.id).addTo(this)
        }
    }




}