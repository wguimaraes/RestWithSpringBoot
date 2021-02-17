package br.com.wguimaraes;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.wguimaraes.converter.NumberConverter;
import br.com.wguimaraes.exception.UnsuportedOperationException;
import br.com.wguimaraes.math.SimpleMath;

@RestController
public class MathController {
	
	private NumberConverter nConverter = new NumberConverter();
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!nConverter.isNumeric(numberOne) || !nConverter.isNumeric(numberTwo)) {
			throw new UnsuportedOperationException("Por favor defina valores numéricos");
		}
		return math.sum(nConverter.convertToDouble(numberOne), nConverter.convertToDouble(numberTwo));  
	}
	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sub(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!nConverter.isNumeric(numberOne) || !nConverter.isNumeric(numberTwo)) {
			throw new UnsuportedOperationException("Por favor defina valores numéricos");
		}
		return math.sub(nConverter.convertToDouble(numberOne), nConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mult(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!nConverter.isNumeric(numberOne) || !nConverter.isNumeric(numberTwo)) {
			throw new UnsuportedOperationException("Por favor defina valores numéricos");
		}
		return math.mult(nConverter.convertToDouble(numberOne), nConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double div(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!nConverter.isNumeric(numberOne) || !nConverter.isNumeric(numberTwo)) {
			throw new UnsuportedOperationException("Por favor defina valores numéricos");
		}
		return math.div(nConverter.convertToDouble(numberOne), nConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/med/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double med(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!nConverter.isNumeric(numberOne) || !nConverter.isNumeric(numberTwo)) {
			throw new UnsuportedOperationException("Por favor defina valores numéricos");
		}
		return math.med(nConverter.convertToDouble(numberOne), nConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value="/square/{number}", method=RequestMethod.GET)
	public Double square(@PathVariable(value="number") String number) throws Exception {
		if(!nConverter.isNumeric(number)) {
			throw new UnsuportedOperationException("Por favor defina um valor numérico");
		}
		return math.square(nConverter.convertToDouble(number));
	}

	
	
}
