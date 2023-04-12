import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from './product.class';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

    private static productslist: Product[] = null;
    private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);

    constructor(private http: HttpClient) { }

    getProducts(): Observable<Product[]> {
        if ( ! ProductsService.productslist )
        {
            this.http.get<any>('http://localhost:8080/api/v1/products').subscribe(data => {
                ProductsService.productslist = data.products;

                this.products$.next(ProductsService.productslist);
                // TODO gestion des erreurs silencieuses
                // this.products$.error((error) => console.log(error));
            });
        }
        else
        {
            this.products$.next(ProductsService.productslist);
        }

        return this.products$;
    }

    create(prod: Product): Observable<Product[]> {
      const headers = { contentType: 'application/json' };
      this.http.post<any>('http://localhost:8080/api/v1/products', prod, { headers }).subscribe(data => {
        ProductsService.productslist = data.products;

        this.products$.next(ProductsService.productslist);
        // TODO gestion des erreurs silencieuses
       // this.products$.error((error) => console.log(error));
      });
      // ProductsService.productslist.push(prod);
      // this.products$.next(ProductsService.productslist);
      return this.products$;
    }

    update(prod: Product): Observable<Product[]>{
      const headers = { contentType: 'application/json' };
      this.http.patch<any>(`http://localhost:8080/api/v1/products/${prod.id}`, prod, { headers }).subscribe(data => {
        ProductsService.productslist = data.products;

        this.products$.next(ProductsService.productslist);
        // TODO gestion des erreurs silencieuses
        // this.products$.error((error) => console.log(error));

      });

     /* ProductsService.productslist.forEach(element => {
          if (element.id === prod.id)
          {
              element.name = prod.name;
              element.category = prod.category;
              element.code = prod.code;
              element.description = prod.description;
              element.image = prod.image;
              element.inventoryStatus = prod.inventoryStatus;
              element.price = prod.price;
              element.quantity = prod.quantity;
              element.rating = prod.rating;
          }
      });
      this.products$.next(ProductsService.productslist);*/
      return this.products$;
    }


    delete(id: number): Observable<Product[]>{
      const headers = { contentType: 'application/json' };
      this.http.delete<any>('http://localhost:8080/api/v1/products/' + id, { headers }).subscribe(data => {
        ProductsService.productslist = data.products;

        this.products$.next(ProductsService.productslist);
        // TODO gestion des erreurs silencieuses
        // this.products$.error((error) => console.log(error));

      });
     /* ProductsService.productslist = ProductsService.productslist.filter(value => value.id !== id );
      this.products$.next(ProductsService.productslist);*/

      return this.products$;
    }
}
