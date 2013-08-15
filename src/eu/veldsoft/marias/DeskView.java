package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 15 Aug 2013
 */
public class DeskView {

	// TODO It is only for project to compile.
	private class Ui_Marias {
	}

	// TODO It is only for project to compile.
	private class GraphicsScene {
	}

	// TODO It is only for project to compile.
	private class GraphicsView {
		public Object scene() {
			return (null);
		}
	}

	// TODO It is only for project to compile.
	private class SignalMapper {
	}

	// TODO It is only for project to compile.
	private class Pixmap {
	}

	// TODO It is only for project to compile.
	private class Point {
	}

	// TODO It is only for project to compile.
	private class GraphicsEllipseItem {
	}

	// TODO It is only for project to compile.
	private class Settings {
	}

	// TODO It is only for project to compile.
	private class Font {
	}

	// TODO It is only for project to compile.
	private class GraphicsItem {

		public String data(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		public GraphicsItem parentItem() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(DeskView.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * instance
	 */
	private static DeskView instance;

	/**
	 * 
	 */
	private GraphicsScene graphicsScene;

	/**
	 * 
	 */
	private GraphicsView graphicsView;

	/**
	 * 
	 */
	private SignalMapper signalMapper;

	/**
	 * Pointer to game
	 */
	private Game game;

	/**
	 * Pointers to graphics items - cards. They are only moved from one position
	 * to another instead of creating and destroying.
	 */
	private CardItem cardItem[] = new CardItem[32];

	/**
	 * Image of the card back.
	 */
	private Pixmap rub;

	/**
	 * Images of cards.
	 */
	private Pixmap images[] = new Pixmap[32];

	/**
	 * Tromf icons images.
	 */
	private Pixmap suits[] = new Pixmap[4];

	/**
	 * Console output.
	 */
	private List<String> out = new ArrayList<String>();

	/**
	 * positions in the graphics view for each of the 3 players: stack
	 */
	private Point poziciaKopy[] = new Point[3];

	/**
	 * cards (upper left corner of the leftmost card)
	 */
	private Point poziciaKariet[] = new Point[3];

	/**
	 * tromf icon
	 */
	private Point poziciaTromfu[] = new Point[3];

	/**
	 * name label
	 */
	private Point poziciaMena[] = new Point[3];

	/**
	 * where stich(trick) goes when a players takes it
	 */
	private Point poziciaStichov[] = new Point[3];

	/**
	 * where supertext is animated
	 */
	private Point poziciaTextu[] = new Point[3];

	/**
	 * deck and then talon
	 */
	private Point poziciaBase;

	/**
	 * graphics items of forhont light
	 */
	private GraphicsEllipseItem lightSimple[] = new GraphicsEllipseItem[3];

	/**
	 * 
	 */
	private GraphicsEllipseItem lightGradient[] = new GraphicsEllipseItem[3];

	/**
	 * 
	 */
	private Settings settings;

	/**
	 * 
	 */
	private Font font;

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static DeskView Instance() {
		if (instance == null) {
			LOGGER.info("DeskView: no instance to get");
		}
		return instance;
	}

	/**
	 * Instance should be created only once. Now it is created by Marias class.
	 * It takes two arguments - ui of the marias for access to graphics view and
	 * game for access to state of the game and players
	 * 
	 * @param ui
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void createInstance(Ui_Marias ui, Game g) {
		if (instance != null) {
			LOGGER.info("DeskView: create instance called twice");
		}

		instance = new DeskView(ui, g);
	}

	/**
	 * Instance is created by Marias, so it is also destroyed only by Marias.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void destroyInstance() {
		if (instance == null) {
			LOGGER.info("DeskView: destroying null class");
		}

		instance = null;
	}

	/**
	 * Cleans graphics scene of graphics items, so they won't be stored in
	 * memory anymore. By default, cards and animations are not removed, because
	 * cards are not drawed, only moved, so they stay. Animations are removed
	 * after they are finished.
	 * 
	 * @param all
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void cleanGraphics(boolean all) {
		Instance()._cleanGraphics(all);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void cleanGraphics() {
		cleanGraphics(false);
	}

	/**
	 * Gathers all cards from desk, cleans all animations if some remains and
	 * recreates the cards at deck.
	 * 
	 */
	public static void gather() {
		// TODO To be done by ...
	}

	public static void intro() {
		// TODO To be done by ...
	}

	/**
	 * Moves card with id cid to position so it appears at the hand of player
	 * with id pid at position offset. It is used during card dealing and in
	 * case when a players plays a card and a gap arises in the hand - the cards
	 * are shifted then. Note: offset is zero-based.
	 * 
	 */
	public static void rozdaj(int cid, int pid, int offset, int pause,
			boolean last) {
		// TODO To be done by ...
	}

	public static void rozdaj(int cid, int pid, int offset, int pause) {
		rozdaj(cid, pid, offset, pause, false);
		// TODO To be done by ...
	}

	/**
	 * Talon is a special keyword of this game. Google translated it as "kitty",
	 * but it evokes me some kind of cat:) The forhont chooses 2 cards that
	 * other players don't see and he is not playing with that cards. After
	 * this, all players has exactly 10 cards. Talon consists of that 2 cards
	 * and is located at the position of the former card pile. cid is id of the
	 * card, forhont wants to give away. If the flag last is on, it means, the
	 * last card has been given to talon and the finish of the animation is
	 * connected to next action.
	 * 
	 */
	public static void talon(int cid, boolean last) {
		// TODO To be done by ...
	}

	public static void talon(int cid) {
		talon(cid, false);
	}

	/**
	 * Moves cards in player's hand to their correct positions.
	 * 
	 */
	public static void fixHand(int pid, boolean last) {
	}

	public static void fixHand(int pid) {
		fixHand(pid, false);
	}

	/**
	 * Tromf is also a special keyword, it is translated as "trump". It is a
	 * "chosen card" - chosen by forhont. The color(or suit) of the tromf will
	 * be stronger than other colors. At the start of the bidding, it is
	 * revealed to other players. Tromf is then displayed in front of forhont,
	 * so players can remember the chosen color. This function is only called
	 * after tromf has been chosen, so it only ejects the card softly to be
	 * visible.
	 * 
	 */
	public static void ejectTromf(int cid, boolean reverse) {
		// TODO To be done by ...
	}

	public static void ejectTromf(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Sets the card image. If the former was back of the card, now it will be
	 * front.
	 * 
	 */
	public static void revealCard(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Sets the card image to back. The value of the card will therefore be
	 * hidden.
	 * 
	 */
	public static void hideCard(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Draws light at forhont, name labels with money, console with game
	 * messages and tromf suit.
	 * 
	 */
	public static void draw() {
		// TODO To be done by ...
	}

	/**
	 * Cleans all the graphics and displays results. They are stored in
	 * game.stav.hra.results
	 * 
	 */
	public static void drawResults() {
		// TODO To be done by ...
	}

	/**
	 * Animate card cid from player pid to the stack (poziciaKopy[pid])
	 * 
	 */
	public static void animateCard(int cid, int pid) {
		// TODO To be done by ...
	}

	/**
	 * Animate stich(trick) - all 3 cards at the stack(kopa) are transferred to
	 * player pid.
	 * 
	 */
	public static void animateStich(int pid) {
		// TODO To be done by ...
	}

	/**
	 * deprecated
	 */
	public static void show() {
		// TODO To be done by ...
	}

	/**
	 * Dirty hook how anyone can easily get pointer to Game (Game have access to
	 * any class).
	 * 
	 */
	public static Game getGame() {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * Prints animated text s that says player with id pos. Also logs it.
	 * 
	 */
	public static void print(String s, int pos) {
		// TODO To be done by ...
	}

	public static void print(String s) {
		print(s, 0);
	}

	/**
	 * Adds given string to console output.
	 * 
	 */
	public static void log(String s) {
		// TODO To be done by ...
	}

	public DeskView(Ui_Marias ui, Game g) {
		// TODO To be done by ...
	}

	/**
	 * ...
	 * 
	 * @param all
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public void _cleanGraphics(boolean all) {
		// TODO for(GraphicsItem git : graphicsView.scene().items()){
		// if (git.data(0).equals("card") == false
		// && git.data(0).equals("anim") == false
		// && git.parentItem().data(0).equals("anim") == false) {
		//
		// graphicsView.scene().removeItem(git);
		//
		// if (git.data(0).equals("light") == false) {
		// }
		// } else if (all == true) {
		// if (git.data(0).equals("light") == false) {
		// graphicsView.scene().removeItem(git);
		// }
		// }
		// }
	}

	public void _cleanGraphics() {
		_cleanGraphics(false);
	}

	public void _gather() {
		// TODO To be done by ...
	}

	public void _intro() {
		// TODO To be done by ...
	}

	public void _rozdaj(int cid, int pid, int offset, int pause, boolean last) {
		// TODO To be done by ...
	}

	public void _rozdaj(int cid, int pid, int offset, int pause) {
		_rozdaj(cid, pid, offset, pause, false);
	}

	public void _fixHand(int pid, boolean last) {
		// TODO To be done by ...
	}

	public void _talon(int cid, boolean last) {
		// TODO To be done by ...
	}

	public void _ejectTromf(int cid, boolean reverse) {
		// TODO To be done by ...
	}

	public void _revealCard(int cid) {
		// TODO To be done by ...
	}

	public void _hideCard(int cid) {
		// TODO To be done by ...
	}

	public void _draw() {
		// TODO To be done by ...
	}

	public void _drawResults() {
		// TODO To be done by ...
	}

	public void _animateCard(int cid, int pid) {
		// TODO To be done by ...
	}

	public void _animateStich(int pid) {
		// TODO To be done by ...
	}

	public void _show() {
		// TODO To be done by ...
	}

	public Game _getGame() {
		// TODO To be done by ...
		return (null);
	}

	public void _print(String s, int pos) {
		// TODO To be done by ...
	}

	public void _log(String s) {
		// TODO To be done by ...
	}
}

// #include "deskview.h"
//
// DeskView* DeskView.instance = null;
//
// DeskView.DeskView(Ui_Marias* ui, Game* g)
// {
// game = g;
// signalMapper = new QSignalMapper();
// QObject.connect(signalMapper,SIGNAL(mapped(int)),game,SLOT(animationFinished(int)));
// graphicsView = ui.graphicsView;
// graphicsScene = new QGraphicsScene(graphicsView.sceneRect());
// graphicsView.setScene(graphicsScene);
// settings = new QSettings("marias.ini", QSettings.IniFormat);
//
// //INIT IMAGES
//
// QString fName = settings.value("cards/images","img/classic7").toString();
// QDir dir(fName);
// if (!dir.exists())
// qWarning() << QObject.tr("Cannot find the directory: ") << fName << "now: "
// << QDir.current();
// QDirIterator it(dir);
// while (it.hasNext()) {
// qDebug() << it.next();
// if(it.fileInfo().isFile()){
// qDebug() << it.fileInfo().baseName();
// if(it.fileInfo().baseName().contains('s')){
// int k = it.fileInfo().baseName()[1].digitValue();
// if(k<4 && k>=0){
// suits[k] = QPixmap(it.filePath());
// qDebug() << "suit loaded: " << k;
// }
// }else{
// int k = it.fileInfo().baseName().toInt();
// if(k<32 && k>=0){
// images[k] = QPixmap(it.filePath());
// qDebug() << "image loaded: " << k;
// }
// }
// }
// }
// rub = QPixmap(settings.value("cards/rub","img/rub.png").toString());
// qDebug() << "rub loaded";
//
// //INIT ITEM POSITIONS
//
// poziciaKopy[0] = QPoint(330,275);
// poziciaKopy[1] = QPoint(295,235);
// poziciaKopy[2] = QPoint(365,245);
//
// poziciaKariet[0] = QPoint(200,445);
// poziciaKariet[1] = QPoint(70,50);
// poziciaKariet[2] = QPoint(400,50);
//
// poziciaTromfu[0] = QPoint(300,395);
// poziciaTromfu[1] = QPoint(50,160);
// poziciaTromfu[2] = QPoint(420,160);
//
// poziciaMena[0] = QPoint(550,395);
// poziciaMena[1] = QPoint(100,160);
// poziciaMena[2] = QPoint(480,160);
//
// poziciaStichov[0] = QPoint(700,445);
// poziciaStichov[1] = QPoint(1,50);
// poziciaStichov[2] = QPoint(700,50);
//
// poziciaTextu[0] = QPoint(400,350);
// poziciaTextu[1] = QPoint(200,150);
// poziciaTextu[2] = QPoint(600,150);
//
// poziciaBase = QPoint(700,200);
//
// //INIT LIGHT
//
// int cx[3],cy[3],r[3];
// cx[0]=600;cy[0]=800;r[0]=500;
// cx[1]=-100;cy[1]=-100;r[1]=500;
// cx[2]=700;cy[2]=-200;r[2]=500;
// for(int i=0;i<3;i++){
// QRadialGradient gradient(cx[i], cy[i], r[i]);
// gradient.setColorAt(0, QColor.fromRgbF(1, 1, 1, 1));
// gradient.setColorAt(1, QColor.fromRgbF(0, 0, 0, 0));
// QBrush brush(gradient);
// lightGradient[i] = new
// QGraphicsEllipseItem(cx[i]-r[i],cy[i]-r[i],2*r[i],2*r[i]);
// lightGradient[i].setPen(QColor.fromRgb(255,255,255,0));
// lightGradient[i].setBrush(brush);
// lightGradient[i].setData(0,"light");
// }
// lightSimple[0] = new QGraphicsEllipseItem(100,300,800,600);
// lightSimple[1] = new QGraphicsEllipseItem(-300,-300,800,600);
// lightSimple[2] = new QGraphicsEllipseItem(300,-300,800,600);
// for(int i=0;i<3;i++){
// lightSimple[i].setPen(QColor.fromRgb(255,255,255,0));
// lightSimple[i].setBrush(QBrush(QColor.fromRgb(255,255,255,i==0?15:25),Qt.SolidPattern));
// lightSimple[i].setData(0,"light");
// }
// font = QFont();
// }
//
// DeskView.~DeskView(){
// delete graphicsScene;
// delete graphicsView;
// delete settings;
// }
//
// DeskView* DeskView.Instance(){
// if(instance==null){
// LOGGER.info("DeskView: no instance to get");
// }
// return instance;
// }
//
// void DeskView.createInstance(Ui_Marias* ui, Game* g){
// if(instance!=null){
// LOGGER.info("DeskView: create instance called twice");
// }
// instance = new DeskView(ui,g);
// }
//
// void DeskView.destroyInstance(){
// if(instance==null){
// LOGGER.info("DeskView: destroying null class");
// }
// delete instance;
// instance=null;
// }
//
// void DeskView._cleanGraphics(bool all){
// foreach(QGraphicsItem* git,graphicsView.scene().items()){
// if(git.data(0)!="card" && git.data(0)!="anim" &&
// git.parentItem().data(0)!="anim"){
// graphicsView.scene().removeItem(git);
// if(git.data(0)!="light")
// delete git;
// }else if(all){
// if(git.data(0)!="light")
// graphicsView.scene().removeItem(git);
// delete git;
// }
// }
// }
//
// void DeskView.cleanGraphics(bool all){
// Instance()._cleanGraphics(all);
// }
//
// void DeskView._gather(){
// cleanGraphics(true);
// for(int i=0;i<32;i++){
// CardItem* ci = new CardItem();
// ci.setPixmap(rub);
// ci.setPos(poziciaBase);
// ci.setZValue(i);
// ci.setOpacity(1);
// ci.cid = i;
// ci.setData(0,"card");
// ci.setData(1,i);
// graphicsView.scene().addItem(ci);
// cardItem[i] = ci;
// }
// }
//
// void DeskView.gather(){
// Instance()._gather();
// }
//
// void DeskView._intro(){
// gather();
// int n = settings.value("intro/length",10).toInt();
// int delay = settings.value("graphics/delay",100).toInt();
// QList<QList<qreal> > x;
// QList<QList<qreal> > y;
// for(int i=0;i<32;i++){
// x.push_back(QList<qreal>());
// y.push_back(QList<qreal>());
// for(int j=0;j<n;j++){
// x[i].push_back(rand()%700);
// y[i].push_back(rand()%500);
// }
// revealCard(i);
// }
// QParallelAnimationGroup* xy = new QParallelAnimationGroup();
// for(int i=0;i<32;i++){
// QSequentialAnimationGroup* xs = new QSequentialAnimationGroup();
// QSequentialAnimationGroup* ys = new QSequentialAnimationGroup();
// cardItem[i].setPos(x[i][0],y[i][0]);
// for(int j=1;j<n;j++){
// QPropertyAnimation* ax = new QPropertyAnimation(cardItem[i],"x");
// ax.setStartValue(x[i][j-1]);
// ax.setEndValue(x[i][j]);
// ax.setDuration(15*delay+rand()%(5*delay));
// ax.setEasingCurve(QEasingCurve.InOutSine);
// xs.addAnimation(ax);
//
// QPropertyAnimation* ay = new QPropertyAnimation(cardItem[i],"y");
// ay.setStartValue(y[i][j-1]);
// ay.setEndValue(y[i][j]);
// ay.setDuration(15*delay+rand()%(5*delay));
// ay.setEasingCurve(QEasingCurve.InOutSine);
// ys.addAnimation(ay);
// }
// QPropertyAnimation* ax = new QPropertyAnimation(cardItem[i],"x");
// ax.setStartValue(x[i][n-1]);
// ax.setEndValue(poziciaBase.x());
// ax.setDuration(15*delay+rand()%(5*delay));
// ax.setEasingCurve(QEasingCurve.InOutSine);
// xs.addAnimation(ax);
//
// QPropertyAnimation* ay = new QPropertyAnimation(cardItem[i],"y");
// ay.setStartValue(y[i][n-1]);
// ay.setEndValue(poziciaBase.y());
// ay.setDuration(15*delay+rand()%(5*delay));
// ay.setEasingCurve(QEasingCurve.InOutSine);
// ys.addAnimation(ay);
//
// xy.addAnimation(xs);
// xy.addAnimation(ys);
// }
// signalMapper.setMapping(xy,game.stav.kolo);
// QObject.connect(xy,SIGNAL(finished()),signalMapper,SLOT(map()));
// xy.start(QAbstractAnimation.DeleteWhenStopped);
//
// }
//
// void DeskView.intro(){
// Instance()._intro();
// }
//
// void DeskView._rozdaj(int cid, int pid, int offset, int pause, bool last){
// //qDebug() << "rozdavam " << cid << " " << pid << " " << offset;
// cardItem[cid].setZValue(offset);
// QSequentialAnimationGroup* rozd = new QSequentialAnimationGroup();
// QPropertyAnimation* anim = new QPropertyAnimation(cardItem[cid],"pos");
// anim.setEndValue(poziciaKariet[pid]+QPoint(offset*(pid==0?30:20),0));
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// if(pause>0)
// rozd.addPause(pause*settings.value("graphics/delay",100).toInt());
// rozd.addAnimation(anim);
// if(last){
// signalMapper.setMapping(rozd,game.stav.kolo);
// QObject.connect(rozd,SIGNAL(finished()),signalMapper,SLOT(map()));
// }
// rozd.start(QAbstractAnimation.DeleteWhenStopped);
// }
//
// void DeskView.rozdaj(int cid, int pid,int offset,int pause, bool last){
// Instance()._rozdaj(cid,pid,offset,pause,last);
// }
//
// void DeskView._fixHand(int pid,bool last){
// int lastBad=0;
// for(int i=0;i<game.players[pid].hand.count();i++){
// int cid = game.players[pid].hand[i];
// cardItem[cid].setZValue(i);
// if(cardItem[cid].x() != poziciaKariet[pid].x()+i*(pid==0?30:20))
// lastBad=i;
// }
// for(int i=0;i<game.players[pid].hand.count();i++){
// int cid = game.players[pid].hand[i];
// if(cardItem[cid].x() != poziciaKariet[pid].x()+i*(pid==0?30:20)){
// QPropertyAnimation* anim = new QPropertyAnimation(cardItem[cid],"x");
// anim.setEndValue(poziciaKariet[pid].x()+i*(pid==0?30:20));
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// if(last && i==lastBad){
// signalMapper.setMapping(anim,game.stav.kolo);
// QObject.connect(anim,SIGNAL(finished()),signalMapper,SLOT(map()));
// }
// anim.start(QAbstractAnimation.DeleteWhenStopped);
// }
// }
// }
//
// void DeskView.fixHand(int pid,bool last){
// Instance()._fixHand(pid,last);
// }
//
// void DeskView._talon(int cid,bool last){
// cardItem[cid].setPixmap(rub);
// QPropertyAnimation* anim = new QPropertyAnimation(cardItem[cid],"pos");
// anim.setEndValue(poziciaBase);
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// if(last){
// signalMapper.setMapping(anim,game.stav.kolo);
// QObject.connect(anim,SIGNAL(finished()),signalMapper,SLOT(map()));
// }
// anim.start(QAbstractAnimation.DeleteWhenStopped);
// }
//
// void DeskView.talon(int cid,bool last){
// Instance()._talon(cid,last);
// }
//
// void DeskView._ejectTromf(int cid,bool reverse){
// int vysunutie=((game.stav.forhont==0)^reverse)?-120:120;
// cardItem[cid].setY(cardItem[cid].y()+vysunutie);
// if(game.stav.forhont!=0)hideCard(cid);
// }
//
// void DeskView.ejectTromf(int cid,bool reverse){
// Instance()._ejectTromf(cid,reverse);
// }
//
// void DeskView._revealCard(int cid){
// cardItem[cid].setPixmap(images[cid]);
// }
//
// void DeskView.revealCard(int cid){
// Instance()._revealCard(cid);
// }
//
// void DeskView._hideCard(int cid){
// cardItem[cid].setPixmap(rub);
// }
//
// void DeskView.hideCard(int cid){
// Instance()._hideCard(cid);
// }
//
// void DeskView._draw(){
// qDebug() << "start draw";
// cleanGraphics(false);
//
// //DRAW FORHONT LIGHT
//
// if(settings.value("graphics/light_type","simple")=="gradient")
// graphicsScene.addItem(lightGradient[game.stav.forhont]);
// else if(settings.value("graphics/light_type","simple")=="simple")
// graphicsScene.addItem(lightSimple[game.stav.forhont]);
//
// //DRAW LABELS
//
// font.setBold(false);
// font.setFamily("Monospace");
// font.setPointSize(12);
//
// for(int i=0;i<3;i++){
// QGraphicsTextItem* text = new QGraphicsTextItem();
// text.setHtml(game.players[i].name +
// ": <span style=\"font-size: xx-large;\">" +
// QString.number(game.players[i].peniaze)+QString.fromUtf8(" xxx</span>"));
// text.setPos(poziciaMena[i]);
// text.setFont(*&font);
// if(game.stav.forhont==i)
// text.setDefaultTextColor(QColor("white"));
// else
// text.setDefaultTextColor(QColor("yellow"));
// graphicsScene.addItem(text);
// }
//
// // DRAW CONSOLE
//
// font.setBold(false);
// font.setPointSize(9);
// int i=0;
// QString o="";
// foreach(QString s,out){
// if(i==0){
// font.setPointSize(11);
// font.setBold(true);
// }else{
// font.setPointSize(9);
// font.setBold(false);
// }
// QGraphicsTextItem* output = new QGraphicsTextItem("> "+s);
// output.setPos(10,350 - i);
// output.setFont(*&font);
// output.setDefaultTextColor(QColor("yellow"));
// graphicsScene.addItem(output);
// i += 6 + font.pointSize();
// }
//
// // DRAW TROMF SUIT
//
// if(game.stav.forhont>-1 && game.stav.hra.tromf>-1){
// QGraphicsPixmapItem* pi = new QGraphicsPixmapItem();
// pi.setPixmap(suits[game.stav.hra.tromf/8]);
// pi.setPos(poziciaTromfu[game.stav.forhont]);
// pi.setZValue(1000);
// graphicsView.scene().addItem(pi);
// }
// }
//
// void DeskView.draw(){
// return Instance()._draw();
// }
//
// void DeskView._drawResults(){
// qDebug() << "start draw results";
// cleanGraphics(1);
// draw();
//
// QFont font = QFont();
// font.setBold(false);
// font.setFamily("Monospace");
// font.setPointSize(12);
//
// QGraphicsTextItem* text = new QGraphicsTextItem();
// QString
// html="<table cellpadding=\"2\" cellspacing=\"0\" border=\"1\"><tr><td align=\"center\" colspan=\"2\">"+QString.number(game.stav.hra.forhontPoints)+":"+QString.number(game.stav.hra.oppPoints)+"</td></tr>";
// typedef QTriple<QString,QString,bool> ResRow;
// foreach(ResRow row,game.stav.res){
// html+="<tr"+QString(row.third?" style=\"font-weight:bold;\"":"")+"><td>"+row.first+"</td><td align=\"right\">"+row.second+"</td></tr>";
// if(row.third)
// html+="<tr><td></td><td></td></tr>";
// }
// html+="</table><a href=\"#\" style=\"color:white;\">"+QObject.tr("New game")+"</a>";
//
// text.setHtml(html);
// text.setPos(QPoint(270,220));
// text.setFont(*&font);
// text.setDefaultTextColor(QColor("white"));
// text.setTextInteractionFlags(Qt.TextBrowserInteraction);
// text.setCursor(Qt.PointingHandCursor);
// QObject.connect(text,SIGNAL(linkActivated(QString)),game,SLOT(newGame()));
// graphicsScene.addItem(text);
// }
//
// void DeskView.drawResults(){
// Instance()._drawResults();
// }
//
// void DeskView._animateCard(int cid,int pid){
// cardItem[cid].setPixmap(images[cid]);
// QParallelAnimationGroup* hodKartu= new QParallelAnimationGroup();
// QPropertyAnimation* anim = new QPropertyAnimation(cardItem[cid],"pos");
// anim.setEndValue(poziciaKopy[pid]);
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// hodKartu.addAnimation(anim);
// anim = new QPropertyAnimation(cardItem[cid],"z");
// anim.setEndValue(game.stav.kopa.count());
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// hodKartu.addAnimation(anim);
// signalMapper.setMapping(hodKartu,game.stav.kolo);
// QObject.connect(hodKartu,SIGNAL(finished()),signalMapper,SLOT(map()));
// hodKartu.start(QAbstractAnimation.DeleteWhenStopped);
// return;
// }
//
// void DeskView.animateCard(int cid,int pid){
// Instance()._animateCard(cid,pid);
// }
//
// void DeskView._animateStich(int pid){
// qDebug() << "animate stich " << pid;
// for(int i=0;i<3;i++)
// fixHand(i);
//
// QParallelAnimationGroup* berStich = new QParallelAnimationGroup();
// for(int i=0;i<3;i++){
// //qDebug() << "Adding anim"<< i << ": " << game.stav.kopa[i];
// cardItem[game.stav.kopa[i]].setZValue(cardItem[game.stav.kopa[i]].zValue()-1000+100*game.stav.kolo);
// QPropertyAnimation* anim = new
// QPropertyAnimation(cardItem[game.stav.kopa[i]],"pos");
// anim.setEndValue(poziciaStichov[pid]);
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// berStich.addAnimation(anim);
// anim = new QPropertyAnimation(cardItem[game.stav.kopa[i]],"opacity");
// anim.setEndValue(0);
// anim.setDuration(3*settings.value("graphics/delay",100).toInt());
// berStich.addAnimation(anim);
// }
// QSequentialAnimationGroup* sag = new QSequentialAnimationGroup();
// sag.addPause(7*settings.value("graphics/delay",100).toInt());
// sag.addAnimation(berStich);
//
// signalMapper.setMapping(sag,game.stav.kolo);
// QObject.connect(sag,SIGNAL(finished()),signalMapper,SLOT(map()));
// sag.start(QAbstractAnimation.DeleteWhenStopped);
// }
//
// void DeskView.animateStich(int pid){
// Instance()._animateStich(pid);
// }
//
// void DeskView._show(){
// graphicsView.show();
// }
//
// void DeskView.show(){
// return Instance()._show();
// }
//
// Game* DeskView._getGame(){
// return game;
// }
//
// Game* DeskView.getGame(){
// return Instance()._getGame();
// }
//
// void DeskView._print(QString s,int pos){
//
// //nice animation
//
// //qDebug() << "printing on pos " << pos << ": " <<
// QString.number(poziciaTextu[pos].x()) << ":" <<
// QString.number(poziciaTextu[pos].y());
// int textWidth = 750;
//
// font.setBold(false);
// font.setFamily("Monospace");
// font.setPointSize(50);
//
// SuperText* sText = new SuperText();
// sText.setHtml("<p align=\"center\">"+s+"</p>");
// sText.setTextWidth(textWidth);
// sText.setData(0,"anim");
// if(pos>2 || pos<0)pos=0;
// sText.setPos(poziciaTextu[pos]-QPoint(textWidth/2,0));
// sText.setFont(*&font);
// sText.setDefaultTextColor(QColor("yellow"));
// graphicsScene.addItem(sText);
//
// QPropertyAnimation* superTextAnim = new QPropertyAnimation(sText,"opacity");
// superTextAnim.setDuration(10*settings.value("graphics/delay",100).toInt());
// superTextAnim.setEndValue(0);
// superTextAnim.setEasingCurve(QEasingCurve.InQuad);
// superTextAnim.start(QAbstractAnimation.DeleteWhenStopped);
//
// _log(s);
// _draw();
// }
//
// void DeskView.print(QString s,int pos){
// Instance()._print(s,pos);
// }
//
// void DeskView._log(QString s){
// if(out.count()>5)out.pop_back();
// out.push_front(s);
// }
//
// void DeskView.log(QString s){
// Instance()._log(s);
// }
