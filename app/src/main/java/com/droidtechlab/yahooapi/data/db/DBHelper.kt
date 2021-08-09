package com.droidtechlab.yahooapi.data.db

import com.droidtechlab.yahooapi.data.db.entities.*
import com.droidtechlab.yahooapi.data.response.*

object DBHelper {

    @JvmStatic
    fun toMatchEntity(matchDetail: MatchDetail): MatchInfoEntity {
        return matchDetail.run {
            MatchInfoEntity(
                id = 0,
                weather = weather,
                status = status,
                league = match?.leauge,
                result = result,
                series = series?.name,
                venue = venue?.name,
                umpires = officials?.umpires,
                referee = officials?.referee,
                manOfMatch = playerMatch,
                dateTime = match?.date,
                tossWonBy = tossWonBy
            )
        }
    }

    @JvmStatic
    fun toPlayerEntity(
        teamFullName: String?,
        teamShortName: String?,
        teamId: String?,
        id: String?,
        players: Players
    ): PlayerEntity {
        return players.run {
            PlayerEntity(
                _id = id?.toInt()!!,
                fullName = nameFull,
                position = position,
                battingInfo = toBattingEntity(batting ?: Batting()),
                bowlingInfo = toBowlingEntity(bowling ?: Bowling()),
                teamId = teamId,
                teamFullName = teamFullName,
                teamShortName = teamShortName

            )
        }
    }

    @JvmStatic
    fun toBattingEntity(batting: Batting): BattingEntity {
        return batting.run {
            BattingEntity(
                style = style,
                average = average,
                strikeRate = strikeRate,
                runs = runs
            )
        }
    }

    @JvmStatic
    fun toBowlingEntity(bowling: Bowling): BowlingEntity {
        return bowling.run {
            BowlingEntity(
                style = style,
                average = average,
                economyRate = economyRate,
                wickets = wickets
            )
        }
    }

    @JvmStatic
    fun toInningsEntity(innings: Innings): InningsEntity {
        return innings.run {
            InningsEntity(
                _id = 0,
                battingTeam = battingTeam,
                number = number,
                total = total,
                wickets = wickets,
                overs = overs,
                runRate = runRate,
                byes = byes,
                legByes = legByes,
                wides = wides,
                noBalls = noBalls,
                penalty = penalty,
                allotedOvers = allotedOvers,
                batsmanList = toBatsmenEntity(batsmenObj),
                bowlerList = toBowlerEntity(bowlersObj),
                currentartnerShip = toCurrentPartnerShipEntity(
                    currentPartnership ?: CurrentPartnerShip()
                ),
                powerPlay = toPowerPlayEntity(powerPlay ?: PowerPlay())
            )
        }
    }

    @JvmStatic
    private fun toPowerPlayEntity(powerPlay: PowerPlay): PowerPlayEntity {
        return powerPlay.run {
            PowerPlayEntity(
                pp1 = pp1,
                pp2 = pp2
            )
        }
    }

    @JvmStatic
    private fun toCurrentPartnerShipEntity(currentPartnership: CurrentPartnerShip): CurrentPartnerShipEntity {
        return currentPartnership.run {
            CurrentPartnerShipEntity(
                runs = runs,
                balls = balls,
                partnerBatsmen = toParnerBatsmanEntity(partnerBatsmen ?: emptyList())
            )
        }
    }

    @JvmStatic
    private fun toParnerBatsmanEntity(partnerBatsmenList: List<PartnerBatsmen>): List<PartnerBatsmenEntity> {
        val listOfPartnerBatsmanEntity = mutableListOf<PartnerBatsmenEntity>()
        partnerBatsmenList.forEach {
            it.apply {
                listOfPartnerBatsmanEntity.add(
                    PartnerBatsmenEntity(
                        runs = runs,
                        balls = balls,
                        batsmanId = batsmanId
                    )
                )
            }
        }
        return listOfPartnerBatsmanEntity
    }

    @JvmStatic
    private fun toBowlerEntity(bowlersList: List<BowlersObj>?): List<BowlerInInningsEntity> {
        val listOfBowlerEntity = mutableListOf<BowlerInInningsEntity>()
        bowlersList?.forEach { bowlerObj ->
            bowlerObj.apply {
                listOfBowlerEntity.add(
                    BowlerInInningsEntity(
                        bowler = bowler,
                        overs = overs,
                        runs = runs,
                        maidens = maidens,
                        wickets = wickets,
                        economyRate = economyRate,
                        noBalls = noBalls,
                        wides = wides,
                        dots = dots
                    )
                )
            }
        }
        return listOfBowlerEntity
    }

    @JvmStatic
    private fun toBatsmenEntity(batsmenList: List<BatsmenObj>?): List<BatsmenInInningsEntity> {
        val listOfBatsmanEntity = mutableListOf<BatsmenInInningsEntity>()
        batsmenList?.forEach { batsmenObj ->
            batsmenObj.apply {
                listOfBatsmanEntity.add(
                    BatsmenInInningsEntity(
                        batsman = batsman,
                        runs = runs,
                        balls = balls,
                        fours = fours,
                        sixes = sixes,
                        dots = dots,
                        strikeRate = strikeRate,
                        dismissal = dismissal,
                        howOut = howOut,
                        bowler = bowler,
                        fielder = fielder

                    )
                )
            }
        }
        return listOfBatsmanEntity
    }
}