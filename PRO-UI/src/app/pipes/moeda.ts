import { Pipe, PipeTransform } from '@angular/core';
import { parse } from 'querystring';

@Pipe({
    name: 'moeda'
})
export class Moeda implements PipeTransform
{
    transform(
        value: any,
        currencySign: string = 'R$ ',
        decimalLength: number = 2,
        chunkDelimiter: string = '.',
        decimalDelimiter: string = ',',
        chunkLength: number = 3
        ): 
        string{
        
            if (value != null) {
                let result = '\\d(?=(\\d{' + chunkLength + '})+' + (decimalLength > 0 ? '\\D' : '$') + ')';
                value = new Number(value);
                let num = value.toFixed(Math.max(0, ~~decimalLength));
                return currencySign + (decimalDelimiter ? num.replace('.', decimalDelimiter) : num).replace(new RegExp(result, 'g'), '$&' + chunkDelimiter);
                }
   
            return null;
        }
}
