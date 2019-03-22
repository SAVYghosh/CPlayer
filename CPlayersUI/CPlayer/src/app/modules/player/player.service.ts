import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from './player';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {


  newPlayer:Player;
  apiKey: string;
  watchlistEndpoint: string;
  searchEndipoint: string;
  playerId: any;

  constructor(private http: HttpClient) {
    this.apiKey = "1YasD9n15lRgkMe8RPs6epPRNbh1";


    this.watchlistEndpoint = "http://localhost:8085/api/v1/playerservice/player";
  }


  searchPlayer(searchKey: string): Observable<any>{
    let searchUrl : string =  "https://cricapi.com/api/playerFinder?apikey="+this.apiKey+"&name="+searchKey+"";
    return this.http.get(searchUrl);

  }

  addPlayerToFavlist(player) {
    
    return this.http.post(this.watchlistEndpoint, player);
  }

  getFavouritePlayers(): Observable<Array<any>> {
    return this.http.get<Array<any>>(this.watchlistEndpoint);
  }

  deletePlayerFromWatchlist(player) {
    const delUrl = `${this.watchlistEndpoint}/${player.id}`;
    return this.http.delete(delUrl, {responseType: 'text'});
  }

  showDetails(plid){
    console.log("service");
    console.log(plid);
    this.playerId=plid;

  }
  savepId(plid){
    this.playerId=plid;
  }

  showDetailsnow(){
    
   let Url2 : string =  "https://cricapi.com/api/playerStats?apikey="+this.apiKey+"&pid="+this.playerId;
    return this.http.get(Url2);
  }

}
