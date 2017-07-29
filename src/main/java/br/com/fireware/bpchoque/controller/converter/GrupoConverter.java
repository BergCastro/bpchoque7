package br.com.fireware.bpchoque.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.thymeleaf.util.StringUtils;

import br.com.fireware.bpchoque.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Grupo grupo = new Grupo();
			grupo.setId(Long.valueOf(id));
			return grupo;
		}
		
		return null;
	}

}
