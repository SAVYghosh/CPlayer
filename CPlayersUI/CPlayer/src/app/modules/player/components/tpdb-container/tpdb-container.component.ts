import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../player.service';
import { Player } from '../../player';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'player-tpdb-container',
  templateUrl: './tpdb-container.component.html',
  styleUrls: ['./tpdb-container.component.css']
})
export class TpdbContainerComponent implements OnInit {

  players: Array<Player>;
  playerType: string;
  playerDetail: any;
  matchType: any;

  constructor(private service: PlayerService, private route: ActivatedRoute) {
    this.players = [];
    this.route.data.subscribe((data) => {
      this.playerType = data.playerType;
      this.matchType=this.playerDetail.data.bowling.type.ODIs;
    });
  }

  ngOnInit() {
    this.service.playerId;
    this.service.showDetailsnow().subscribe((data)=>{
      console.log(data);
      this.playerDetail=data;    
    })
  }

}
